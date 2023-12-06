package org.scoula.security.service;


import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import org.scoula.security.domain.AuthVO;
import org.scoula.security.domain.MemberVO;
import org.scoula.security.domain.SignupVO;
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
    public static final String AVATAR_UPLOAD_DIR = "c:/upload/avatar";

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
        uploadAvatar(member);
    }


    private void uploadAvatar(SignupVO member) {
        MultipartFile avatar = member.getAvatar();
        if(!avatar.isEmpty()) {
            File dest = new File(AVATAR_UPLOAD_DIR, member.getUsername() + ".png");
            try {
                Thumbnails.of(avatar.getInputStream())
                        .size(50, 50)
                        .toFile(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public File getAvatar(String username) {
        File avatar = new File(MemberServiceImpl.AVATAR_UPLOAD_DIR, username + ".png");
        if(!avatar.exists()) {	// 파일이 존재하지 않으면
            avatar = new File(MemberServiceImpl.AVATAR_UPLOAD_DIR, "unknown.png");
        }

        return avatar;
    }

}
