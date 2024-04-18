package com.example.blog.admin.controller;

import com.example.blog.admin.model.params.PageParam;
import com.example.blog.admin.pojo.SysUser;
import com.example.blog.admin.service.UserService;
import com.example.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user/userList")
    public Result userList(@RequestBody PageParam pageParam){
        return userService.listUser(pageParam);
    }

    @PostMapping("user/update")
    public Result update(@RequestBody SysUser sysUser) {
        return userService.update(sysUser);
    }

    @GetMapping("user/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
}
