package com.ypy.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("get_bot_info/")
    public Map<String, String> getBotInfo() {
        Map<String, String> bot1 = new HashMap<String, String>();
        bot1.put("name", "Anon");
        bot1.put("rating", "1500");
        return bot1;
    }
}
