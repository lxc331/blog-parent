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
		if (username == null) return "fail";
		else {
			long uid=loginservice.getUidByName(username);
			httpSession.setAttribute("uid", uid);
			return "chatroom";
		}
	}

	/**
	 * 从httpSession的用户id中获得用户名并获取返回一个User
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/currentuser",method = RequestMethod.GET)
	@ResponseBody
	public User currentuser(HttpSession httpSession){
		Long uid = (Long)httpSession.getAttribute("uid");
		String name = loginservice.getNameById(uid);
		return new User(uid, name);
	}
  }
