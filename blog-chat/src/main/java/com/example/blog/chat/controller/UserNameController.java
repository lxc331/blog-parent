package com.example.blog.chat.controller;

import com.example.blog.chat.service.UserNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserNameController {
    @Autowired
    UserNameService userNameService;

    @RequestMapping("/current_user")
    public String loginValidate(HttpSession httpSession) {
        return userNameService.selectCurrentUserName(httpSession);
    }
}
