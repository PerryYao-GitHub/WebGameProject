package com.webgame.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {  // 本质上是一个字典, 代表了表的一行; 类名一定要跟表名一致

    @TableId(type = IdType.AUTO)
    private Integer id;  // 这里一定要用 Integer 不能是 int

    private String username;

    private String password;

    private String profile;
}
