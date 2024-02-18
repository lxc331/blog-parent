package com.example.blog.controller;

import com.example.blog.service.RegisterService;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    //后端传递多个参数，前端只选用其需要的参数就可以了
    @PostMapping
    public Result register(@RequestBody RegisterParam registerParam) {
        return registerService.register(registerParam);
    }
}
