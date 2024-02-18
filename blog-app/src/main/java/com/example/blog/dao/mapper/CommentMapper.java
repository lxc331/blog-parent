package com.example.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
