package com.example.blog.chat.service;

import com.example.blog.chat.pojo.User;

import javax.servlet.http.HttpSession;

public interface LoginService {

	String loginValidate(String username, HttpSession httpSession);

	User currentUser(HttpSession httpSession);

	Long getUidByName(String name);

}
