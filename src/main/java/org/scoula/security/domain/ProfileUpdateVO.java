package org.scoula.security.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProfileUpdateVO {
    private String username;

    @NotBlank(message="비밀번호는 필수 항목입니다.")
    private String password;

    @NotBlank(message="이메일은 필수 항목입니다.")
    @Email(message="email 형식에 맞지 않습니다.")
    private String email;

    private MultipartFile avatar;
}
