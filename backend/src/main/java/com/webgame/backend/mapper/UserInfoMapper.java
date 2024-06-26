package com.webgame.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webgame.backend.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {  // 代表了user_info那张表
}
