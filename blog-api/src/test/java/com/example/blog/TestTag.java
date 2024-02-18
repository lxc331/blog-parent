package com.example.blog;

import com.example.blog.dao.mapper.TagMapper;
import com.example.blog.dao.pojo.Article;
import com.example.blog.dao.pojo.Tag;
import com.example.blog.service.TagService;
import com.example.blog.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class TestTag {
    @Autowired
    private TagMapper tagMapper;
    @Test
    public void testTag() {

        Article article = new Article();

        List<Tag> tagList = tagMapper.findTagsByArticleId(1l);
        for (Tag tag : tagList)
            System.out.println(tag);
    }

}
