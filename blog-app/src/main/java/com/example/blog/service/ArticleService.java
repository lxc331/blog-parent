package com.example.blog.service;


import com.example.blog.vo.Result;
import com.example.blog.vo.params.ArticleParam;
import com.example.blog.vo.params.PageParams;

public interface ArticleService {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result HotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result NewArticle(int limit);
    /**
     * 首页，文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 文章发布服务
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);


    /**
     * 文章搜索
     * @param search
     * @return
     */
    Result searchArticle(String search);

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    Result delete(Long articleId);
}
