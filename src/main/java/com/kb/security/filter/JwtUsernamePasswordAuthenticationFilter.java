package com.kb.security.filter;


import com.kb.user.service.KakaoLoginService;
import com.kb.user.dto.request.LoginDTO;
import com.kb.user.dto.User;
import com.kb.user.service.UserService;
import com.kb.security.handler.LoginFailureHandler;
import com.kb.security.handler.LoginSuccessHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Setter(onMethod = @__({@Autowired}))
    private UserService userService;

    @Setter(onMethod = @__({@Autowired}))
    private KakaoLoginService kakaoLoginService;

    @Setter(onMethod = @__({@Autowired}))
    private UserDetailsService userDetailsService;


    // 스프링 생성자 주입을 통해 전달
    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);

        setFilterProcessesUrl("/api/auth/login");                  // POST 로그인 요청 url
        setAuthenticationSuccessHandler(loginSuccessHandler);    // 로그인 성공 핸들러 등록
        setAuthenticationFailureHandler(loginFailureHandler);  // 로그인 실패 핸들러 등록
    }

    // 로그인 요청 URL인 경우 로그인 작업 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        LoginDTO login = LoginDTO.of(request);

        // 인증 토큰(UsernamePasswordAuthenticationToken) 구성
        UsernamePasswordAuthenticationToken authenticationToken = null;

        try {
            if(login.getCode() == null) throw new IllegalAccessException();
            else {
                //현재 토큰에 해당하는 카카오 회원 이메일과 일치하는 가입된 회원 조회
                String enrollUrl = "http://localhost:5173/auth/kakaologin";
                String token = kakaoLoginService.getToken(login.getCode(), enrollUrl);
                Map<String, Object> map = kakaoLoginService.getUserInfo(token);
                String kakaoId = (String) map.get("email");
                User user = userService.getMemberByKakaoId(kakaoId);

                UserDetails principal = userDetailsService.loadUserByUsername(user.getUsername());

                authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error("로그인을 위한 코드가 없습니다.");
        }

        // 인증 요청
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
