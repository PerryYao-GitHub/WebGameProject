package com.webgame.backend.service.impl.user.account;

import com.webgame.backend.config.UserDetailsImpl;
import com.webgame.backend.pojo.UserInfo;
import com.webgame.backend.service.user.account.LoginService;
import com.webgame.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);  // 如果登录失败, 会自动处理
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        UserInfo loginUserInfo = loginUser.getUserInfo();  // 获取对应用户名的用户信息

        String jwt = JwtUtil.createJWT(String.valueOf(loginUserInfo.getId()));  // 传入用户id, 生成jwt token

        Map<String, String> resp = new HashMap<>();
        resp.put("msg", "success");
        resp.put("token", jwt);
        return resp;  // 返回包含JWT的Map
    }
}
