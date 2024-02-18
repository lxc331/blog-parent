package com.example.blog.service;

import com.example.blog.vo.Result;
import com.example.blog.vo.TagVo;

import java.util.List;

public interface TagService {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 最热标签分页
     * @param limit
     * @return
     */
    Result hots(int limit);

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAll();

    /**
     * 查看所有标签
     * @return
     */
    Result findAllDetail();

    /**
     *标签文章列表
     * @param id
     * @return
     */
    Result findADetailById(Long id);
}
