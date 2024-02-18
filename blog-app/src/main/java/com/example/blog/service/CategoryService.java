package com.example.blog.service;

import com.example.blog.vo.CategoryVo;
import com.example.blog.vo.Result;

public interface CategoryService {
    public CategoryVo findCategoryById(Long id);

    Result findAll();

    /**
     * 查看所有文章分类
     * @return
     */
    Result findAllDetail();

    /**
     * 分类文章列表
     * @param id
     * @return
     */
    Result categoriesDetailById(Long id);
}
