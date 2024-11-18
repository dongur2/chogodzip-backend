package com.kb.user.dto;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class User implements UserDetails {
    private Long userId;
    private String username;
    private String nickname;
    private String loginType;
    private String pic;

    private String interestGu;

    private Date createdAt;
    private Date modifiedAt;
    private Boolean isDeleted;

    private String accessToken;     //JWT
    private String refreshToken;    //JWT

    // 복수개의 권한을 관리
    // 문자열data("ROLE_USER", "ROLE_ADMIN")를 처리할 수 있는 GrantedAuthority의 하위클래스
    private List<SimpleGrantedAuthority> authorities; // authorities

    /**
     * Collection - List/Set
     *
     * Collection<? extends GrantedAuthority>
     * 	- <GrantedAuthority를 상속하는 ?> -> 자식타입(상한선)
     *  - <? super Integer> -> Integer 부모타입 (하한선)
     * Collection<GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }

    //가입 전 유효성 검사
    public boolean checkRequiredValue(){
        try {
            return (username.isEmpty() || nickname.isEmpty());
        }catch (Exception e){
            return false;
        }
    }

}
