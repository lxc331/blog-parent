package com.example.blog.admin.controller;

import com.example.blog.admin.model.params.PageParam;
import com.example.blog.admin.pojo.Permission;
import com.example.blog.admin.service.PermissionService;
import com.example.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result permissionList(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

}
