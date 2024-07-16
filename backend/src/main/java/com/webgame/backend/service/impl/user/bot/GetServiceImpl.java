package com.webgame.backend.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.mapper.BotInfoMapper;
import com.webgame.backend.pojo.BotInfo;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.bot.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetServiceImpl implements GetService {
    @Autowired
    private BotInfoMapper botInfoMapper;

    @Override
    public List<BotInfo> getUserOwnBots() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) token.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();

        QueryWrapper<BotInfo> q = new QueryWrapper<>();
        q.eq("user_id", loginUserInfo.getId());  // query wrapper 中的列名要用数据库中的原始列名

        return  botInfoMapper.selectList(q);
    }
}
