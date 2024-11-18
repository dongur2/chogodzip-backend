package com.kb.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth implements GrantedAuthority {
    private Long userId;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
