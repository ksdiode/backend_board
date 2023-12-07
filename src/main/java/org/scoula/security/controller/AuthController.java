package org.scoula.security.controller;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import org.scoula.security.domain.*;
import org.scoula.security.service.MemberService;
import org.scoula.security.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

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
        log.info("signup:" + member);

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
    public void avatar(@PathVariable("username") String username, HttpServletResponse response) throws IOException {

        response.setHeader("Content-Type", "image/png");
        Files.copy(Avatar.get(username), response.getOutputStream());
    }

    @GetMapping("/profile")
    public void profile(@AuthenticationPrincipal CustomUser customUser, Model model) {
        model.addAttribute("member", customUser.getMember());
    }

    @GetMapping("/update")
    public void update(@AuthenticationPrincipal CustomUser customUser, Model model) {
        model.addAttribute("member", customUser.getMember());
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("member") ProfileUpdateVO member, Errors errors,
                         @AuthenticationPrincipal CustomUser customUser) {
        member.setUsername(customUser.getUsername());
        log.info("update:" + member);
        log.info("customUser:" + customUser);

        // 기본 유효성 검사
        if(errors.hasErrors()) {
            return "auth/update";
        }

        // 비밀번호 확인
        if(!service.checkPassword(member.getPassword(), customUser.getMember().getPassword())) {
            errors.rejectValue("password", "비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
            return "auth/update";
        }

        // DB 저장
        service.update(member);

        // custom user의 세부 정보 교체
        customUser.setMember(service.get(customUser.getUsername()));

        return "redirect:/auth/profile";
    }

    @GetMapping("/change_password")
    public void changePassword(@ModelAttribute("member") PasswordChangeVO member, @AuthenticationPrincipal CustomUser customUser) {
        member.setUsername(customUser.getUsername());
    }

    @PostMapping("/change_password")
    public String changePassword(@Valid @ModelAttribute("member") PasswordChangeVO member, Errors errors,
                                 @AuthenticationPrincipal CustomUser customUser) {
        member.setUsername(customUser.getUsername());

        // 기본 유효성 검사
        if(errors.hasErrors()) {
            return "auth/change_password";
        }

        // 새 비밀번호 확인 일치 검사
        if(!member.checkPassword()) {
            errors.rejectValue("password2", "비밀번호 불일치", "새 비밀번호 확인이 일치하지 않습니다.");
            return "auth/change_password";
        }

        // 기존 비밀번호 확인
        if(!service.checkPassword(member.getOldPassword(), customUser.getMember().getPassword())) {
            errors.rejectValue("oldPassword", "기존 비밀번호 불일치", "기존 비밀번호가 일치하지 않습니다.");
            return "auth/change_password";
        }

        // DB 저장
        service.changePassword(member);
        // custom user의 세부 정보 교체
        customUser.setMember(service.get(customUser.getUsername()));

        return "redirect:/auth/profile";
    }
}
