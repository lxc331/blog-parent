package com.example.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.blog.dao.pojo.SysUser;
import com.example.blog.service.RegisterService;
import com.example.blog.service.SysUserService;
import com.example.blog.utils.JWTUtils;
import com.example.blog.vo.ErrorCode;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.RegisterParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //加密盐用于加密
    private static final String slat = "example!@#";
    @Override
    public Result register(RegisterParam registerParam) {
        /**
         * 1. 判断参数 是否合法
         * 2. 判断账户是否存在，存在 返回账户已经被注册
         * 3. 不存在，注册用户
         * 4. 生成token
         * 5. 存入redis 并返回
         * 6. 注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
         */
         String account = registerParam.getAccount();
         String password = registerParam.getPassword();
         String nickname = registerParam.getNickname();
         if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)) {
             return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
         }
         SysUser sysUser = this.sysUserService.findUserByAccount(account);
         if (sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
         }

         sysUser = new SysUser();
         sysUser.setAccount(account);
         sysUser.setPassword(DigestUtils.md5Hex(password + slat));
         sysUser.setNickname(nickname);
         sysUser.setCreateDate(System.currentTimeMillis());
         sysUser.setLastLogin(System.currentTimeMillis());
         sysUser.setAvatar("/static/img/logo.b3a48c0.png");
         sysUser.setAdmin(1);
         sysUser.setDeleted(0);
         sysUser.setSalt("");
         sysUser.setStatus("");
         sysUser.setEmail("");
         this.sysUserService.save(sysUser);

        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        // JSON.toJSONString 用法    https://blog.csdn.net/antony9118/article/details/71023009
        //过期时间是一百天
        //redisTemplate用法  https://blog.csdn.net/lydms/article/details/105224210
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),100, TimeUnit.DAYS);

        return Result.success(token);
    }
}
