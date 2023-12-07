package org.scoula.security.service;

import org.scoula.security.domain.MemberVO;
import org.scoula.security.domain.PasswordChangeVO;
import org.scoula.security.domain.ProfileUpdateVO;
import org.scoula.security.domain.SignupVO;

import java.io.File;


public interface MemberService {
    public MemberVO get(String username);

    public void create(SignupVO member);

    public boolean checkPassword(String rawPassword, String encPassword);

    public void update(ProfileUpdateVO member);

    public void changePassword(PasswordChangeVO member);
}
