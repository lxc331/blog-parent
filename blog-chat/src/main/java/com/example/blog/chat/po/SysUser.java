package com.example.blog.chat.po;

import lombok.Data;

@Data
public class SysUser {
    //@TableId(type = IdType.ASSIGN_ID)，默认分布式id
    //以后 用户多了之后，要进行分表操作，id就要用分布式id了，而不是默认的自增id
    //mybatis-plus默认采用分布式
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}

