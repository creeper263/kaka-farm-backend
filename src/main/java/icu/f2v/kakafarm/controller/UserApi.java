package icu.f2v.kakafarm.controller;

import com.alibaba.fastjson.JSON;
import icu.f2v.kakafarm.persistence.entity.User;
import icu.f2v.kakafarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/user")
public class UserApi {
    @Autowired
    UserService userService;

    @GetMapping("/onLogin")
    public String onLogin(@RequestParam("code") String code) {
        User user = null;
        int success = 1;
        try {
            user = userService.login(code);
        } catch (IOException e) {
            e.printStackTrace();
            success = -1;
        }
        LoginResponse lr = new LoginResponse(success, user);

        return JSON.toJSONString(lr);
    }

    static class LoginResponse {
        int success;
        User user;

        public LoginResponse(int success, User user) {
            this.success = success;
            this.user = user;
        }
    }
}
