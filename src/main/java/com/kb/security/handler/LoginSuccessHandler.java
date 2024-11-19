package com.kb.security.handler;

import com.kb.user.dto.User;
import com.kb.security.util.JsonResponse;
import com.kb.security.util.JwtProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProcessor jwtProcessor;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 인증 결과 Principal
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtProcessor.generateAccessToken(user);
        String refreshToken = jwtProcessor.generateRefreshToken(user.getUsername());

        //refresh token은 cookie에 저장
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true); // js에서 접근 불가
//        refreshTokenCookie.setSecure(true); // https에서만 요청 가능
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(30 * 60); // 쿠키 유효 기간 30분
        response.addCookie(refreshTokenCookie);

        JsonResponse.send(response, accessToken);
    }
}
