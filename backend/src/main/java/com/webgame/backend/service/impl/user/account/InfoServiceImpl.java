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
        UsernamePasswordAuthenticationToken upToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) upToken.getPrincipal();
        UserInfo userInfo = loginUser.getUserInfo();

        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("msg", "success");
        infoMap.put("id", String.valueOf(userInfo.getId()));
        infoMap.put("username", userInfo.getUsername());
        infoMap.put("profile", userInfo.getProfile());
        return infoMap;
    }
}
