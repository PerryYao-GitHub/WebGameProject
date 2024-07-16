package com.webgame.backend.controller.user.account;

import com.webgame.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/account")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String> request) {
        String username = request.get("username");  // 前端传入的字段名必须为 username 和 password
        String password = request.get("password");
        return loginService.getToken(username, password);
    };
}
