package com.example.blog.chat.utils;

import com.example.blog.chat.po.SysUser;

public class UserThreadLocal {
    //不让他自行new出来
    private UserThreadLocal(){};
    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
