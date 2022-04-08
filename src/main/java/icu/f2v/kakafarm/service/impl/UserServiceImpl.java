package icu.f2v.kakafarm.service.impl;

import com.alibaba.fastjson.JSON;
import icu.f2v.kakafarm.persistence.entity.User;
import icu.f2v.kakafarm.persistence.repo.UserRepository;
import icu.f2v.kakafarm.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    OkHttpClient client = new OkHttpClient();

    String appId = null;
    String secret = null;
    String grant_type = "authorization_code";

    @Override
    public User login(String code) throws IOException {
        String authUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s", appId, secret, code, grant_type);
        Request request = new Request.Builder().url(authUrl).build();
        Response response = client.newCall(request).execute();
        String openid = JSON.parseObject(Objects.requireNonNull(response.body()).string(), IResponse.class).openid;
        if (userRepository.existsById(openid)) {
            return userRepository.findById(openid);
        }
        User user = new User();
        user.setId(openid);
        user.setName(newRandomName());
        userRepository.save(user);
        return user;
    }

    public String newRandomName() {
        String id = UUID.randomUUID().toString().substring(0, 8);
        return "用户" + id;
    }

    static class IResponse {
        String openid;
        String session_key;
        String unionid;
        String errcode;
        String errmsg;
    }
}
