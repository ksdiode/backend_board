package org.scoula.security.controller;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import org.scoula.security.domain.MemberVO;
import org.scoula.security.domain.SignupVO;
import org.scoula.security.service.MemberService;
import org.scoula.security.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@Log4j
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    MemberService service;


    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }

    @GetMapping("/signup")
    public void signup(@ModelAttribute("member") SignupVO member) {

    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("member") SignupVO member, Errors errors) throws IOException {
        log.info("update:" + member);

        // 기본 유효성 검사
        if(errors.hasErrors()) {
            return "auth/signup";
        }

        // username 중복 검사
        if(service.get(member.getUsername()) != null) {
            errors.rejectValue("username", "ID 중복", "이미 사용중인 ID입니다.");
            return "auth/signup";
        }

        // 비밀번호 확인 일치 검사
        if(!member.checkPassword()) {
            errors.rejectValue("password2", "비밀번호 불일치", "비밀번호 확인이 일치하지 않습니다.");
            return "auth/signup";
        }
        
        // DB 저장
        service.create(member);

        return "redirect:/";
    }

    @GetMapping("/avatar/{username}")
    @ResponseBody
    public void avatar(@PathVariable("username") String username,
                      HttpServletResponse response) throws IOException {

        File avatar = service.getAvatar(username);

        response.setHeader("Content-Type", "image/png");
        Files.copy(avatar.toPath(), response.getOutputStream());
    }
}
