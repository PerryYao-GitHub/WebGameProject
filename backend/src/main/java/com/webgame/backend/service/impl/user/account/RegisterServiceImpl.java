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
        Map<String, String> resp = new HashMap<>();
        // username
        if (username == null || username.trim().isEmpty()) {
            resp.put("msg", "username can not be empty");
            return resp;
        }
        if (username.length() < 6 || username.length() > 64) {
            resp.put("msg", "username must be between 6 and 64 characters");
            return resp;
        }

        // password
        if (password == null || password.isEmpty() || confirmedPassword.isEmpty()) {
            resp.put("msg", "password can not be empty");
            return resp;
        }
        if (password.length() < 6 || password.length() > 64) {
            resp.put("msg", "password must be between 6 and 64 characters");
            return resp;
        }
        if (!password.equals(confirmedPassword)) {
            resp.put("msg", "passwords do not match");
            return resp;
        }


        // 判断是否存在重复的用户名
        QueryWrapper<UserInfo> q = new QueryWrapper<>();
        q.eq("username", username);
        if (! userInfoMapper.selectList(q).isEmpty()) {
            resp.put("msg", "username already exists");
            return resp;
        }

        password = passwordEncoder.encode(password);
        String profile = "https://cdn.acwing.com/media/user/profile/photo/196144_lg_c8550d9b9c.jpg";
        UserInfo userInfoAdded = new UserInfo(null, username, password, profile);
        userInfoMapper.insert(userInfoAdded);

        resp.put("msg", "success");
        return resp;
    }
}
