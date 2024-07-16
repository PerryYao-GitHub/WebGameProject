package com.webgame.backend.service.impl.user.bot;

import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.mapper.BotInfoMapper;
import com.webgame.backend.pojo.BotInfo;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {
    @Autowired
    private BotInfoMapper botInfoMapper;

    @Override
    public Map<String, String> add(Map<String, String> req) {
        Map<String, String> resp = new HashMap<>();

        // 根据token取得是哪个用户传的bot信息
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) token.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();
//        if (loginUserInfo == null) {
//            resp.put("msg", "no such user");
//            return resp;
//        }

        String title = req.get("title");
        String description = req.get("description");
        String content = req.get("content");

        if (title.trim().isEmpty() || title.length() > 64) {
            resp.put("msg", "title must be between 6 and 64 characters");
            return resp;
        }

        if (description == null) {
            description = "This is a lazy man, writing nothing here...";
        }

        if (description.length() > 256) {
            resp.put("msg", "description must be less than 256 characters");
            return resp;
        }

        if (content == null || content.length() > 10000) {
            resp.put("msg", "content must be less than 10000 characters and can't be empty");
            return resp;
        }

        Date nowTime = new Date();
        BotInfo AddedBotInfo = new BotInfo(
                null,
                loginUserInfo.getId(),  // id (user)
                title,
                description,
                content,
                1500,
                nowTime,
                nowTime
        );
        botInfoMapper.insert(AddedBotInfo);
        resp.put("msg", "success");

        return resp;
    }
}
