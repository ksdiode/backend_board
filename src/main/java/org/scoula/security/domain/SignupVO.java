package org.scoula.security.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupVO {
    @NotBlank(message="사용자 ID는 필수 항목입니다.")
    @Size(min = 4, message="사용자 ID는 4글자 이상이어야 합니다.")
    private String username;

    @NotBlank(message="비밀번호는 필수 항목입니다.")
    private String password;

    private String encPassword;

    @NotBlank(message="비밀번호 확인은 필수 항목입니다.")
    private String password2;

    @NotBlank(message="이메일은 필수 항목입니다.")
    @Email(message="email 형식에 맞지 않습니다.")
    private String email;

    private MultipartFile avatar;

    public boolean checkPassword() {
        return password != null && password2 != null & password.equals(password2);
    }

}
