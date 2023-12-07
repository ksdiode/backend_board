package org.scoula.security.domain;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class MemberVO {
    private String username;
    private String password;
    private String email;

    private Date regDate;
    private Date updateDate;

    private List<AuthVO> authList;  // 권한 목록

}
