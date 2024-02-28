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
	
	@RequestMapping("/loginValidate")
	public String loginValidate(@RequestParam("username") String username,@RequestParam("password") String pwd,HttpSession httpSession){
		if(username==null)
			return "login";
		String realPwd=loginservice.getPwdByName(username);
		if(realPwd!=null&&pwd.equals(realPwd))
		{
			long uid=loginservice.getUidByName(username);
			httpSession.setAttribute("uid", uid);
			return "chatroom";
		}else
			return "fail";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession){
		return "login";
	}
	
	@RequestMapping(value="/currentuser",method = RequestMethod.GET)
	@ResponseBody
	public User currentuser(HttpSession httpSession){
		Long uid = (Long)httpSession.getAttribute("uid");
		String name = loginservice.getNameById(uid);
		return new User(uid, name);
	}
  }
