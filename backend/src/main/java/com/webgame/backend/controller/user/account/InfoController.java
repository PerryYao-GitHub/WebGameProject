package com.webgame.backend.controller.user.account;

import com.webgame.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private InfoService is;

    @GetMapping("/user/account/info/")
    public Map<String, String> getInfo() {
        return is.getInfo();
    }
}
