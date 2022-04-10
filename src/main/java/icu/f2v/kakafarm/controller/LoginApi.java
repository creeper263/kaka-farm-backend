package icu.f2v.kakafarm.controller;

import com.alibaba.fastjson.JSON;
import icu.f2v.kakafarm.persistence.entity.User;
import icu.f2v.kakafarm.service.JwtService;
import icu.f2v.kakafarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class LoginApi {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    // 登录并返回一个token
    @GetMapping("/onLogin")
    public String onLogin(@RequestParam("code") String code) {
        int responseCode = 1;
        String id = "";
        try {
            id = userService.login(code);
        } catch (IOException e) {
            responseCode = 0;
            e.printStackTrace();
        }

        String token = "";
        if (responseCode == 1) {
            token = jwtService.getNewToken(id);
        }

        LoginResponse lr = new LoginResponse(responseCode, token);

        return JSON.toJSONString(lr);
    }

    static class LoginResponse {
        int code;
        String token;

        public LoginResponse(int code, String token) {
            this.code = code;
            this.token = token;
        }
    }
}
