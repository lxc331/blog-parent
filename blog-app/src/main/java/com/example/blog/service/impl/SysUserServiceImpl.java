package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog.dao.mapper.SysUserMapper;
import com.example.blog.dao.pojo.SysUser;
import com.example.blog.service.LoginService;
import com.example.blog.service.SysUserService;
import com.example.blog.vo.ErrorCode;
import com.example.blog.vo.LoginUserVo;
import com.example.blog.vo.Result;
import com.example.blog.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private LoginService loginService;

    @Override
    public UserVo findUserVoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("奇迹之路");
        }
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null)  {//防止空指针
            sysUser = new SysUser();
            sysUser.setNickname("样例");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");//按1分页查询，保证不会查到底
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1、token合法性校验
         * 是否为空 ，解析是否成功，redis是否存在
         * 2、如果校验失败，返回错误
         *3、如果成功，返回对应结果 LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (StringUtils.isBlank(token)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(String.valueOf(sysUser.getId()));
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getAccount,account);
        lambdaQueryWrapper.last("limit 1");//确保只能查询一条
        return sysUserMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        //保存用户这 id会自动生成
        //这个地方 默认生成的id是 分布式id 雪花算法
        //mybatis-plus
        this.sysUserMapper.insert(sysUser);
    }

}
