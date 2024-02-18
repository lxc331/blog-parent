package com.example.blog.service;

import com.example.blog.dao.pojo.SysUser;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.LoginParam;

public interface LoginService {
    /**
     * 登录功能
     * @param loginParam
     */
    Result login(LoginParam loginParam);

    /**
     * token校验
     * @param token
     * @return
     */
    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);
}
