package com.example.blog;

import com.example.blog.dao.mapper.ArticleMapper;
import com.example.blog.dao.mapper.TagMapper;
import com.example.blog.dao.pojo.Article;
import com.example.blog.dao.pojo.Tag;
import com.example.blog.vo.params.ArticleParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProject {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testTag() {

        Map<Integer,Integer> map = new HashMap();

        map.put(1,2);


    }



}
