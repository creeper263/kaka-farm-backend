package icu.f2v.kakafarm.service;

import icu.f2v.kakafarm.persistence.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserService {
    String login(String code) throws IOException;
}
