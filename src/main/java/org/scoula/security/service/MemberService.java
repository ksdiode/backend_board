package org.scoula.security.service;

import org.scoula.security.domain.MemberVO;
import org.scoula.security.domain.SignupVO;
import org.springframework.stereotype.Service;

import java.io.File;


public interface MemberService {
    public MemberVO get(String username);
    public void create(SignupVO member);

    public File getAvatar(String username);

}
