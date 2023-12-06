package org.scoula.security.mapper;

import org.scoula.security.domain.AuthVO;
import org.scoula.security.domain.MemberVO;
import org.scoula.security.domain.SignupVO;

public interface MemberMapper {
    public MemberVO get(String username);

    public void create(SignupVO member);

    public void createAuth(AuthVO auth);

}
