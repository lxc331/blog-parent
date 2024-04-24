package com.example.blog.common.cache;

import com.alibaba.fastjson.JSON;
import com.example.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
//aop 定义一个切面，切面定义了切点与通知的关系
@Aspect
@Component
@Slf4j
public class CacheAspect {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.example.blog.common.cache.Cache)") //指定该注解类作为切点
    public void pt(){}

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp){
        try {
            //它代表了目标方法或连接点（JoinPoint(切点)）的签名。Signature 提供了关于被通知（advised）方法的信息，如方法名、参数类型等
            Signature signature = pjp.getSignature();
            //它代表了目标方法或连接点（JoinPoint(切点)）的类名
            String className = pjp.getTarget().getClass().getSimpleName();
            ////它代表了目标方法或连接点（JoinPoint(切点)）调用的方法名
            String methodName = signature.getName();


            Class[] parameterTypes = new Class[pjp.getArgs().length];
            //获取目标方法的参数的数组
            //例如 public Result articleById(@PathVariable("id") Long articleId)， arg[0] = articleId
            Object[] args = pjp.getArgs();

            //参数
            String params = "";
            for(int i=0; i<args.length; i++) {
                if(args[i] != null) {
                    params += JSON.toJSONString(args[i]);
                    parameterTypes[i] = args[i].getClass();
                }else {
                    parameterTypes[i] = null;
                }
            }
            if (StringUtils.isNotEmpty(params)) {
                //加密 以防出现key过长以及字符转义获取不到的情况
                params = DigestUtils.md5Hex(params);
            }
            //获得方法体(方法对象)
            Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            //获取Cache注解
            Cache annotation = method.getAnnotation(Cache.class);
            //缓存过期时间
            long expire = annotation.expire();
            //缓存名称
            String name = annotation.name();
            //先从redis获取
            String redisKey = name+"::"+className+"::"+methodName+"::"+params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isNotEmpty(redisValue)){
                log.info("命中缓存~~~,{},{},{}",name,className,methodName);
                return JSON.parseObject(redisValue, Result.class);
            }
            // 实际执行目标方法(proceed是执行方法的返回对象)，在这之前是前置逻辑，在这之后是后置逻辑
            Object proceed = pjp.proceed();

            redisTemplate.opsForValue().set(redisKey,JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("存入缓存~~~ {},{},{}",name,className,methodName);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Result.fail(-999,"系统错误");
    }
    //注意一个缓存一致性的问题，解决方案：1.使用RocketMq中间件处理 2.当我写数据库的时候及时删除相关缓存
}

