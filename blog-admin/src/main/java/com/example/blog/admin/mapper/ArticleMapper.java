package com.example.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.admin.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select account from ms_sys_user where id = #{id}")
    public String findAccountByAuthorId(@Param("id") Long id);
}
