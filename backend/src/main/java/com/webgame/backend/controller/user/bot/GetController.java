package com.webgame.backend.controller.user.bot;

import com.webgame.backend.pojo.BotInfo;
import com.webgame.backend.service.user.bot.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/bot")
public class GetController {
    @Autowired
    private GetService getService;

    @GetMapping("/get/")
    public List<BotInfo> getUserOwnBots() {
        return getService.getUserOwnBots();
    }
}
