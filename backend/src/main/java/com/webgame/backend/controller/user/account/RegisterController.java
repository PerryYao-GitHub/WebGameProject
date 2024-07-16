package com.webgame.backend.controller.user.account;

import com.webgame.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/account")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register/")
    public Map<String, String> register(@RequestParam Map<String, String> req) {  // 从前端获取包含用户注册信息的map
        String username = req.get("username");
        String password = req.get("password");
        String confirmedPassword = req.get("confirmedPassword");
        return registerService.register(username, password, confirmedPassword);
    }
}
