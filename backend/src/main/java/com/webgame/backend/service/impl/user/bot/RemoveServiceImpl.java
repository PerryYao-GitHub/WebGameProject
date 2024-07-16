package com.webgame.backend.service.impl.user.bot;

import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.mapper.BotInfoMapper;
import com.webgame.backend.pojo.BotInfo;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    BotInfoMapper botInfoMapper;

    @Override
    public Map<String, String> remove(Map<String, String> req) {
        Map<String, String> resp = new HashMap<>();
        // 删除之前要看一看bot的userId 是不是用户的id
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) token.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();

        int botId = Integer.parseInt(req.get("botId"));  // 传入的当前bot id
        BotInfo curBotInfo = botInfoMapper.selectById(botId);  // 根据当前bot id 取出当前bot信息
        if (curBotInfo == null) {
            resp.put("msg", "This bot does not exist");
            return resp;
        }
        if ( !loginUserInfo.getId().equals( curBotInfo.getUserId() ) ) {
            resp.put("msg", "You have no right to remove");
            return resp;
        }

        botInfoMapper.deleteById(botId);
        resp.put("msg", "success");

        return resp;
    }
}
