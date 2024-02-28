package com.example.blog.chat.service;

import com.example.blog.chat.pojo.User;

import javax.servlet.http.HttpSession;

public interface UserNameService {
    String selectCurrentUserName(HttpSession httpSession);//验证是否为当前用户,并返回用户名

    User checkToken(String token);//验证token

}
