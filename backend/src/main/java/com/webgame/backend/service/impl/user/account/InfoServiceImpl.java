package com.webgame.backend.service.impl.user.account;

import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {  // 根据 token 获取用户信息
    @Override
    public Map<String, String> getInfo() {
        Map<String, String> resp = new HashMap<>();

        //// 根据token 取出user的操作
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) token.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();

        resp.put("msg", "success");
        resp.put("id", String.valueOf(loginUserInfo.getId()));
        resp.put("username", loginUserInfo.getUsername());
        resp.put("profile", loginUserInfo.getProfile());
        return resp;
    }
}
