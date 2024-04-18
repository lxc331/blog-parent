package com.example.blog.chat.controller;

import javax.servlet.http.HttpSession;

import com.example.blog.chat.pojo.User;
import com.example.blog.chat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class Login {
	@Autowired
	LoginService loginservice;

	/**
	 * 聊天室用户的验证
	 * @param username
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/loginValidate/{username}")
	public String loginValidate(@PathVariable("username") String username,HttpSession httpSession) {
		return loginservice.loginValidate(username,httpSession);
	}

	/**
	 * 从httpSession的用户id中获得用户名并获取返回一个User
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/currentUser",method = RequestMethod.GET)
	@ResponseBody
	public User currentUser(HttpSession httpSession){
		return loginservice.currentUser(httpSession);
	}
  }
