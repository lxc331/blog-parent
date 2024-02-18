package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog.dao.mapper.TagMapper;
import com.example.blog.dao.pojo.Tag;
import com.example.blog.service.TagService;
import com.example.blog.vo.Result;
import com.example.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;

    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }


    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatis plus 无法实现多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        /**
         * 1.标签所有的文章最多，就是最热标签
         * 2.查询，根据tag_id 分组计数，从大到小排列，取前limit个
         *
         */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)) {
            return Result.success(Collections.emptyList());
        }
        //需求的是tagId 和 tagName 对象
        //select * from tag while id in (1,2,3,4)
        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        List<Tag> tags = this.tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findADetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagVo copy = copy(tag);
        return Result.success(copy);
    }
}
