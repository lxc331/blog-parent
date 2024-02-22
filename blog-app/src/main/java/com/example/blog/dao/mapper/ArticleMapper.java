package com.example.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.dao.pojo.Archives;
import com.example.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();
    //加上@Param，防止参数传递过去后，被xml识别失败
    IPage<Article> listArticle(Page<Article> page, @Param("categoryId")Long categoryId, @Param("tagId")Long tagId, @Param("year")String year, @Param("month")String month);

}
