package com.example.blog.admin.controller;

import com.example.blog.admin.model.params.PageParam;
import com.example.blog.admin.service.ArticleService;
import com.example.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("article/articleList")
    public Result articleList(@RequestBody PageParam pageParam){
        return articleService.articleList(pageParam);
    }

    @GetMapping("article/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }
}
