package icu.f2v.kakafarm.service;

import icu.f2v.kakafarm.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    // 根据userId获取一个新的token
    String getNewToken(String userId);
    // 验证token并返回userId
    String verifyToken(String jwt) throws TokenException;
}
