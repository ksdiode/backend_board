package org.scoula.security.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordChangeVO {
    private String username;

    @NotBlank(message="기존 비밀번호는 필수 항목입니다.")
    private String oldPassword;

    @NotBlank(message="새 비밀번호는 필수 항목입니다.")
    private String password;

    @NotBlank(message="새 비밀번호 확인은 필수 항목입니다.")
    private String password2;
    
    private String encPassword;

    public boolean checkPassword() {
        return password != null && password2 != null & password.equals(password2);
    }

}
