package com.webgame.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import com.webgame.backend.pojo.BotInfo;

@Mapper
public interface BotInfoMapper extends BaseMapper<BotInfo> {
}
