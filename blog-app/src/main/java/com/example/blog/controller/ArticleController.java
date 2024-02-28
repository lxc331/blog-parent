package com.example.blog.controller;

import com.example.blog.common.aop.LogAnnotation;
import com.example.blog.common.cache.Cache;
import com.example.blog.service.ArticleService;
import com.example.blog.vo.Result;
import com.example.blog.vo.params.ArticleParam;
import com.example.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//！！！！！！！！！！！！！！！注意由于缓存的原因，虽然展示的速度变快了，但如果一旦遇到更新之后，短时间内显示不出最新的情况
//json数据交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * 首页，文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    //加上此注解，代表要对此接口记录日志
    @LogAnnotation(module = "文章",operation = "获取文章列表")
    @Cache(expire = 5 * 60 * 1000,name = "list_article")
    public Result ListArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    /**
     * 首页，最热文章
     * @return
     */
    @PostMapping("hot")
    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public Result HotArticle() {
        int limit = 5;
        return articleService.HotArticle(limit);
    }

    /**
     * 首页，最新文章
     * @return
     */
    @PostMapping("new")
    @Cache(expire = 5 * 60 * 1000,name = "new_article")
    public Result NewArticle() {
        int limit = 5;
        return articleService.NewArticle(limit);
    }

    /**
     * 首页，文章归档
     * @return
     */
    @PostMapping("listArchives")
    @Cache(expire = 5 * 60 * 1000,name = "list_archives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    @PostMapping("view/{id}")
    @Cache(expire = 5 * 60 * 1000,name = "view_article")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    //  @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    //  而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    @PostMapping("publish")
    @LogAnnotation(module = "文章",operation = "写文章或更新文章")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

    /**
     * 文章搜索
     * @param articleParam
     * @return
     */
    @PostMapping("search")
    public Result search(@RequestBody ArticleParam articleParam){
        //搜索接口
        String search = articleParam.getSearch();
        return articleService.searchArticle(search);
    }

    /**
     * 文章编辑更新，有id要搜索id
     * @param articleId
     * @return
     */
    @PostMapping("{id}")
    public Result articleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @PostMapping("delete/{id}")
    public Result deleteById(@PathVariable("id") Long articleId){
        return articleService.delete(articleId);
    }
}
