package com.example.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.blog.dao.pojo.SysUser;
import com.example.blog.service.LoginService;
import com.example.blog.service.SysUserService;
import com.example.blog.utils.JWTUtils;
import com.example.blog.vo.ErrorCode;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.LoginParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //加密盐用于加密
    private static final String slat = "example!@#";
    /**
     * 1. 检查参数是否合法
     * 2. 根据用户名和密码去user表中查询 是否存在
     * 3. 如果不存在 登录失败
     * 4. 如果存在 ，使用jwt 生成token 返回给前端
     * 5. token放入redis当中，redis  token：user信息 设置过期时间（相比来说session会给服务器产生压力，这么做也是为了实现jwt的续签）
     *  (登录认证的时候 先认证token字符串是否合法，去redis认证是否存在)
     */
    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }

        password = DigestUtils.md5Hex(password + slat);
        SysUser sysUser = sysUserService.findUser(account,password);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        // JSON.toJSONString 用法    https://blog.csdn.net/antony9118/article/details/71023009
        //过期时间是一百天
        //redisTemplate用法  https://blog.csdn.net/lydms/article/details/105224210
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),100, TimeUnit.DAYS);

        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        //token为空返回null
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String,Object> stringObjectMap = JWTUtils.checkToken(token);
        //解析失败
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        //数据过期了
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        //解析回sysUser对象
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

}
