package com.example.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog.dao.mapper.ArticleMapper;
import com.example.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
    //期望此操作在线程池执行，不影响原有的主线程
    //这里线程池不了解可以去看JUC并发编程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaQueryWrapper<Article> updateWrapper = new LambdaQueryWrapper<>();
        //根据id更新
        updateWrapper.eq(Article::getId ,article.getId());
        //设置一个为了在多线程的环境下线程安全
        //改之前再确认这个值有没有被其他线程抢先修改，类似于CAS操作 cas加自旋，加个循环就是cas
        updateWrapper.eq(Article ::getViewCounts,viewCounts);
        // update article set view_count=100 where view_count=99 and id =111
        //实体类加更新条件
        articleMapper.update(articleUpdate,updateWrapper);

        /*try {
            Thread.sleep(5000);
            System.out.println("update finish--------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
