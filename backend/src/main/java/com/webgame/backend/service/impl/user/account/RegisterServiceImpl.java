package com.webgame.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webgame.backend.mapper.UserInfoMapper;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> respMap = new HashMap<String, String>();
        if (username == null) {
            respMap.put("msg", "username can not be null");
            return respMap;
        }

        if (password == null || confirmedPassword == null) {
            respMap.put("msg", "password can not be null");
            return respMap;
        }

        username = username.trim();
        if (username.length() < 6) {
            respMap.put("msg", "username must be at least 6 characters");
            return respMap;
        }

        if (username.length() > 60) {
            respMap.put("msg", "username must be at most 60 characters");
            return respMap;
        }

        if (password.length() > 60) {
            respMap.put("msg", "password must be at most 60 characters");
            return respMap;
        }

        if (!password.equals(confirmedPassword)) {
            respMap.put("msg", "passwords do not match");
            return respMap;
        }

        QueryWrapper<UserInfo> q = new QueryWrapper<>();
        q.eq("username", username);
        if (! userInfoMapper.selectList(q).isEmpty()) {
            respMap.put("msg", "username already exist");
            return respMap;
        }

        password = passwordEncoder.encode(password);
        String profile = "https://cdn.acwing.com/media/user/profile/photo/196144_lg_c8550d9b9c.jpg";
        UserInfo userInfoAdded = new UserInfo(null, username, password, profile);
        userInfoMapper.insert(userInfoAdded);

        respMap.put("msg", "success");
        return respMap;
    }
}
