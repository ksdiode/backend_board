package org.scoula.security.domain;

import net.coobird.thumbnailator.Thumbnails;
import org.scoula.security.service.MemberServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Avatar {
    // 아바타 이미지 업로드 경로 상수
    public static final String AVATAR_UPLOAD_DIR = "c:/upload/avatar";

    public static void upload(String username, MultipartFile avatar) {
        if(!avatar.isEmpty()) {
            File dest = new File(AVATAR_UPLOAD_DIR, username + ".png");
            try {
                Thumbnails.of(avatar.getInputStream())
                        .size(50, 50)
                        .toFile(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Path get(String username) {
        Path avatar = Paths.get(AVATAR_UPLOAD_DIR, username + ".png");
        if(!Files.exists(avatar)) {	// 파일이 존재하지 않으면
            avatar = Paths.get(AVATAR_UPLOAD_DIR, "unknown.png");
        }

        return avatar;
    }
}
