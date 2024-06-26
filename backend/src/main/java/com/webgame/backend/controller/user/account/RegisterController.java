package com.webgame.backend.controller.user.account;

import com.webgame.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService rs;

    @PostMapping("/user/account/register/")
    public Map<String, String> register(@RequestParam Map<String, String> reqMap) {  // 从前端获取包含用户注册信息的map
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        String confirmedPassword = reqMap.get("confirmedPassword");
        return rs.register(username, password, confirmedPassword);
    }
}
