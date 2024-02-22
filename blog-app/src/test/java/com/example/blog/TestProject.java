package com.example.blog;

import com.example.blog.dao.mapper.ArticleMapper;
import com.example.blog.dao.mapper.TagMapper;
import com.example.blog.dao.pojo.Article;
import com.example.blog.dao.pojo.Tag;
import com.example.blog.vo.params.ArticleParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestProject {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testTag() {

        Article article = new Article();

        List<Tag> tagList = tagMapper.findTagsByArticleId(1l);
        for (Tag tag : tagList)
            System.out.println(tag);
    }

    ArticleParam articleParam = new ArticleParam();


    @Test
    public void publishTest() {
        Long t = Long.valueOf(1);
        articleParam.setId(t);


        Article article = new Article();
        boolean isEdit = false;
        // System.out.println(articleParam.getId()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (articleParam.getId() != null) {
            article = new Article();
            article.setId(articleParam.getId());
            article.setTitle(articleParam.getTitle());
            article.setSummary(articleParam.getSummary());
            article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
            articleMapper.updateById(article);
            isEdit = true;
        }

        System.out.println(article);
    }
}
