package org.scoula.security.mapper;

import org.scoula.security.domain.*;

public interface MemberMapper {
    public MemberVO get(String username);

    public void create(SignupVO member);

    public void createAuth(AuthVO auth);

    public void update(ProfileUpdateVO member);

    public void changePassword(PasswordChangeVO member);
}
