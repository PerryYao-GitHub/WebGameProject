package com.webgame.backend.service.user.bot;

import com.webgame.backend.pojo.BotInfo;

import java.util.List;

public interface GetService {
    List<BotInfo> getUserOwnBots();  // userId 存在token中, 可以不用传参数
}
