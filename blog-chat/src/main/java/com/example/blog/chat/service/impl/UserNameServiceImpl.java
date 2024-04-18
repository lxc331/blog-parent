package com.example.blog.chat.service.impl;

import com.example.blog.chat.pojo.SysUser;
import com.example.blog.chat.pojo.User;
import com.example.blog.chat.service.LoginService;
import com.example.blog.chat.service.UserNameService;
import com.example.blog.chat.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserNameServiceImpl implements UserNameService {
    @Autowired
    LoginService loginService;

    /**
     * 从局部县城变量中拿到用户信息，设置进httpSession中
     * @param httpSession
     * @return
     */
    @Override
    public String selectCurrentUserName(HttpSession httpSession) {
        //拿到当前用户
        SysUser sysUser = UserThreadLocal.get();
        long uid=loginService.getUidByName(sysUser.getAccount());
        httpSession.setAttribute("uid", uid);
        return "chatroom";
    }


}
