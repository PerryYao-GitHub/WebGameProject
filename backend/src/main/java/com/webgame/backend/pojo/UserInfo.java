package com.webgame.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {  // 本质上是一个字典, 代表了表的一行; 类名一定要跟表名一致
    private int id;
    private String username;
    private String password;
    private String profile;
}
