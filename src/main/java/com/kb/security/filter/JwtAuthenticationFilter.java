package com.kb.security.filter;

import com.kb.user.dto.User;
import com.kb.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.security.util.JwtProcessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";   // 끝에 공백 있음

    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        try {
            //토큰이 유효할 경우
            if (token != null && validateToken(token)) {
                Authentication auth = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (ExpiredJwtException e) {
            String refreshToken = resolveRefreshToken(request);

            if (refreshToken != null && validateToken(refreshToken)) {
                // 리프레시 토큰이 유효하면 새로운 액세스 토큰 발급
                String username = jwtProcessor.extractUsername(refreshToken);
                User user = userService.getMemberByKakaoId(username);
                String newAccessToken = jwtProcessor.generateAccessToken(user);

                user.setAccessToken(newAccessToken);
                response.setHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + newAccessToken);

                // 새 액세스 토큰으로 SecurityContext 갱신
                Authentication auth = getAuthentication(newAccessToken);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } else {
                // 리프레시 토큰도 유효하지 않다면 예외 처리
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("토큰이 모두 만료되었습니다. 다시 로그인해주세요.");
                return;
            }
        }

        super.doFilter(request, response, filterChain);
    }

    //헤더에서 액세스 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //헤더에서 리프레쉬 토큰 추출
    private String resolveRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("refresh-token");
        return (refreshToken != null && !refreshToken.isEmpty()) ? refreshToken : null;
    }

    //토큰 검증
    private boolean validateToken(String token) {
        return jwtProcessor.validateToken(token);
    }

    //토큰에서 회원 인증 정보 추출
    private Authentication getAuthentication(String token) {
        String username = jwtProcessor.extractUsername(token);
        UserDetails principal = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

}
