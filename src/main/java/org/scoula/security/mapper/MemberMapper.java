package org.scoula.security.mapper;

import org.scoula.security.domain.MemberVO;

public interface MemberMapper {
    public MemberVO get(String username);
}
