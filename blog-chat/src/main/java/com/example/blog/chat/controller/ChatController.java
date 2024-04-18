package com.example.blog.chat.controller;

import java.util.Set;

import com.example.blog.chat.pojo.User;
import com.example.blog.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ChatController {
	@Autowired
	ChatService chatService;

	/**
	 * 获取当前在线的用户
	 * @param currentUser
	 * @return
	 */
	@RequestMapping("/onlineUsers")
	@ResponseBody
	public Set<String> onlineUsers(@RequestParam("currentUser") String currentUser) {
		return chatService.onlineUsers(currentUser);
	}

	/**
	 * 根据用户账号(用户名)获取uid
	 * @param username
	 * @return
	 */
	@RequestMapping("getuid")
	@ResponseBody
	public User getuid(@RequestParam("username") String username) {
		return chatService.getuid(username);
	}
}
