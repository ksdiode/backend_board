package org.scoula.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO implements GrantedAuthority {
    private String username;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
