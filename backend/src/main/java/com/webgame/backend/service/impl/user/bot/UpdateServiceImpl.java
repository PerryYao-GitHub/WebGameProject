package com.webgame.backend.service.impl.user.bot;

import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.mapper.BotInfoMapper;
import com.webgame.backend.pojo.BotInfo;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    BotInfoMapper botInfoMapper;

    @Override
    public Map<String, String> update(Map<String, String> req) {
        Map<String, String> resp = new HashMap<>();

        // 与remove逻辑类似, 先看看是不是修改的自己的bot
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) token.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();
        int botId = Integer.parseInt(req.get("botId"));
        BotInfo curBotInfo = botInfoMapper.selectById(botId);

        if (curBotInfo == null) {
            resp.put("msg", "This bot does not exist");
            return resp;
        }

        if ( !loginUserInfo.getId().equals( curBotInfo.getUserId() ) ) {
            resp.put("msg", "You have no right to update this bot");
            return resp;
        }

        // 下面与add逻辑类似, 判断新修改的信息是否合法
        String title = req.get("title");
        String description = req.get("description");
        String content = req.get("content");

        // title
        if (title == null || title.trim().isEmpty()) {
            resp.put("msg", "title can not be empty");
            return resp;
        }
        if (title.length() > 64) {
            resp.put("msg", "title is longer than 64 characters");
            return resp;
        }

        // description
        if (description == null || description.isEmpty()) {  // 如果字符串可能为 null，可以先判断是否为 null，然后再使用 isEmpty() 方法或直接判断长度是否为 0
            description = "This is a lazy guy, writing nothing here...";
        }
        if (description.length() > 256) {
            resp.put("msg", "description must be less than 256 characters");
            return resp;
        }

        // content
        if (content == null || content.isEmpty()) {
            resp.put("msg", "content can not be empty");
            return resp;
        }
        if (content.length() > 10000) {
            resp.put("msg", "content is longer than 10000 characters");
            return resp;
        }

        // 更新bot
        BotInfo newBotInfo = new BotInfo(
                curBotInfo.getId(), // id
                curBotInfo.getUserId(),  // userId
                title,
                description,
                content,
                curBotInfo.getRating(),
                curBotInfo.getCreateTime(),  // create time
                new Date()  // modify time
        );

        botInfoMapper.updateById(newBotInfo);
        resp.put("msg", "success");
        return resp;
    }
}
