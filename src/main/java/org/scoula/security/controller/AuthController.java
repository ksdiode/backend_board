package org.scoula.security.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }

}
