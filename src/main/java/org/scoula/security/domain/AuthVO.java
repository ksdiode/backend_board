package org.scoula.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthVO implements GrantedAuthority {
    private String username;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
