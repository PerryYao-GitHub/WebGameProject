package com.webgame.backend.controller.user;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webgame.backend.mapper.UserInfoMapper;
import com.webgame.backend.pojo.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserInfoMapper userInfoMapper;

    /* 查询用户 */
    @GetMapping("/user/all/")
    public List<UserInfo> getAllUsers() {
        return userInfoMapper.selectList(null);
    }

    /* 按照 id 查询单个用户 */
    @GetMapping("/user/{id}/")
    public UserInfo getUserById(@PathVariable int id) {
//        return userInfoMapper.selectById(id);
        QueryWrapper<UserInfo> q = new QueryWrapper<>();
        q.eq("id", id);
        return userInfoMapper.selectOne(q);
    }

    /* 添加用户 */
    @GetMapping("/user/add/{id}/{username}/{password}/")
    public String addUser(@PathVariable int id,
                          @PathVariable String username,
                          @PathVariable String password) {
        UserInfo userInfo = new UserInfo(id, username, password);
        userInfoMapper.insert(userInfo);
        return "add success";
    }

    /* 删除用户 */
    @GetMapping("/user/delete/{id}/")
    public String deleteUser(@PathVariable int id) {
        userInfoMapper.deleteById(id);
        return "delete success";
    }
}
