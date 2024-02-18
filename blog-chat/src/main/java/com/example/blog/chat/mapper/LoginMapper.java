package com.example.blog.chat.mapper;

import com.example.blog.chat.po.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	Staff getPwdByName(String name);

	Staff getNameById(long id);
}
