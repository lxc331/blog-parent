package com.example.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.admin.mapper.ArticleMapper;
import com.example.blog.admin.model.params.PageParam;
import com.example.blog.admin.pojo.Article;
import com.example.blog.admin.vo.ArticleVo;
import com.example.blog.admin.vo.PageResult;
import com.example.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    //注意后端的数据传递展示到前端的时候要进行copy，否则无法精确展示大数(long)
    private ArticleVo copy(Article article) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        articleVo.setTitle(article.getTitle());
        articleVo.setSummary(article.getSummary());
        articleVo.setAccount(articleMapper.findAccountByAuthorId(article.getAuthorId()));
        return articleVo;
    }
    //注意后端的数据传递展示到前端的时候要进行copyList，否则无法精确展示大数(long)，从而使得不仅前端展示错误，并且从前端获取数据时也会出错
    private Page<ArticleVo> copyList(Page<Article> articlePage) {
        Page<ArticleVo> articleVoPage = new Page<>();
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            articleVoList.add(copy(article));
        }
        articleVoPage.setRecords(articleVoList);

        return articleVoPage;
    }

    public Result articleList(PageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.eq(Article::getTitle,pageParam.getQueryString());
        }
        Page<Article> articlePage = articleMapper.selectPage(page,queryWrapper);

        PageResult<ArticleVo> pageResult = new PageResult<>();
        pageResult.setList(copyList(articlePage).getRecords());//注意copyList
        pageResult.setTotal(articlePage.getTotal());
        return Result.success(pageResult);
    }

    public Result delete(Long id) {
        articleMapper.deleteById(id);
        return Result.success(null);
    }
}
