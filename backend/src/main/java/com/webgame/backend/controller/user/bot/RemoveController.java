package com.webgame.backend.controller.user.bot;

import com.webgame.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/bot")
public class RemoveController {
    @Autowired
    private RemoveService removeService;

    @PostMapping("/remove/")
    public Map<String, String> remove(@RequestParam Map<String, String> request) {
        return removeService.remove(request);
    }
}
