package com.webgame.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webgame.backend.mapper.UserInfoMapper;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.config.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {  // 检查用户的details合法与否; 将登录检测与数据库关联, 输入数据库中的用户名和密码正确, 就可以登录

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  // 如何你想以邮箱验证 String email
        QueryWrapper<UserInfo> q = new QueryWrapper<>();
        q.eq("username", username);
        UserInfo selectedUserInfo = userInfoMapper.selectOne(q);
        if (selectedUserInfo == null) {
            throw new RuntimeException("no such user");
        }
        return new UserDetailsImpl(selectedUserInfo);
    }
}
