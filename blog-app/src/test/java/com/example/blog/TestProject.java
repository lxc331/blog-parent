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



}
