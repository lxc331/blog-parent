package com.example.blog.service.threadpoll;

import com.example.blog.service.ArticleService;
import com.example.blog.vo.ArticleMessage;
import com.example.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class UpdateCacheThread {
    @Autowired
    ArticleService articleService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Async("taskExecutor")
    public void updateArticleCache(ArticleMessage message) {
        log.info("收到的消息：{}",message);
        //做什么了，更新缓存
        //1.更新查看文章详情的缓存
        Long articleId = message.getArticleId();
        String params = DigestUtils.md5Hex(articleId.toString());
        String redisKey = "view_article::ArticleController::findArticleById::" + params;
        Result articleResult = articleService.findArticleById(articleId);

        log.info("原本缓存值：{},{}",redisKey, redisTemplate.opsForValue().get(redisKey));
        redisTemplate.delete(redisKey);
        /*try {
            Thread.sleep(2000);
            System.out.println("update finish--------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(articleResult), Duration.ofMillis(5 * 60 * 1000));*/
        log.info("更新了缓存:{}",message);
        log.info("后来缓存值：{},{}",redisKey, redisTemplate.opsForValue().get(redisKey));

        //2.文章列表的缓存 不知道参数，解决办法 直接删除缓存
        Set<String> keys = redisTemplate.keys("list_article*"); //Set特性：元素唯一，无序，可以含有空元素。
        keys.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了文章列表的缓存",s);
        });

    }
}
