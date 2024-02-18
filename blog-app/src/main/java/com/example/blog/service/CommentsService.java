package com.example.blog.service;

import com.example.blog.vo.Result;
import com.example.blog.vo.params.CommentParam;

public interface CommentsService {
    /**
     * 根据文章id查询所有的评论列表
     * @param articleId
     * @return
     */
    Result commentsByArticleId(Long articleId);

    /**
     * 根据前端传过来的添加参数，发表评论
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
