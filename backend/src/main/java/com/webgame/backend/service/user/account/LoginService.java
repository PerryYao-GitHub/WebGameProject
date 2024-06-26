package com.webgame.backend.service.user.account;

import java.util.Map;

public interface LoginService {
    Map<String, String> getToken(String username, String password);  // 根据用户名和密码提供一个JWT; 定义了登录功能的抽象规范
}
