package com.example.blog.controller;

import com.example.blog.service.LoginService;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        //登陆 验证用户 访问用户表
        return loginService.login(loginParam);
    }
}
