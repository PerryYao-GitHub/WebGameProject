package com.webgame.backend.service.user.account;

import java.util.Map;

public interface InfoService {  // 根据 token 获取用户信息
    Map<String, String> getInfo();
}
