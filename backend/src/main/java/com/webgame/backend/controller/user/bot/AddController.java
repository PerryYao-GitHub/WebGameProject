package com.webgame.backend.controller.user.bot;

import com.webgame.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/bot")
public class AddController {
    @Autowired  // 注入接口, 例如注入mapper(接口)也要加上
    private AddService addService;

    @PostMapping("/add/")
    public Map<String, String> add(@RequestParam Map<String, String> request) {
        return addService.add(request);
    }
}
