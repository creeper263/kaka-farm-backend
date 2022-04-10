package icu.f2v.kakafarm.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    // 微信小程序对应的用户openId是唯一的，直接作为User实体Id
    @Id
    private String id;

    private String name;
}
