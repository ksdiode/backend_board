package org.scoula.security.service;


import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import org.scoula.security.domain.*;
import org.scoula.security.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper mapper;

    @Autowired
    private PasswordEncoder pwEncoder;

    @Override
    public MemberVO get(String username) {
        return mapper.get(username);
    }

    @Override
    public void create(SignupVO member) {
        // 1. 비밀번호 암호화
        String encPassword = pwEncoder.encode(member.getPassword());
        member.setEncPassword(encPassword);

        // 2. tbl_member에 저장
        mapper.create(member);

        // 3. tbl_member_auth에 저장
        AuthVO auth = new AuthVO(member.getUsername(), "ROLE_USER");
        mapper.createAuth(auth);

        // 4. avatar 이미지 저장
        Avatar.upload(member.getUsername(), member.getAvatar());
    }



    @Override
    public boolean checkPassword(String rawPassword, String encPassword) {
        log.info(rawPassword + "-------------" + encPassword);
        return pwEncoder.matches(rawPassword, encPassword);
    }

    @Override
    public void update(ProfileUpdateVO member) {
        mapper.update(member);
        Avatar.upload(member.getUsername(), member.getAvatar());
    }

    @Override
    public void changePassword(PasswordChangeVO member) {
        // 비밀번호 암호화
        String encPassword = pwEncoder.encode(member.getPassword());
        member.setEncPassword(encPassword);
        mapper.changePassword(member);
    }
}
