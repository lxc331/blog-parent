package com.example.blog.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.chat.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {

}
