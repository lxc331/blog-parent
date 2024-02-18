# 1.工程简介 
基于springboot + vue + websocket 的前后端分离可交流博客平台

# 2. 工程搭建
## 前端的工程：
对blog-api内config里的跨域配置要注意

```
npm install
npm run build
npm run dev
```

## 数据库配置
用mysql运行blog.sql文件

## 下载redis

## 管理员模块运行

[点击此处启动权限管理](http://localhost:8889/pages/main.html) （即为http://localhost:8889/pages/main.html）

## 备注

**注意换掉里面的mysql端口与redis端口，换成你自己的。**

**另外三个后端模块有不同的端口(blog-admin,blog-app,blog-chat)，和一个前端(blog-api)模块的跨域配置的端口要注意，你可以直接用我的配置，也可以换成你的配置。**

# 3.技术总结
1）jwt+redis：token令牌的登陆方式、访问速度快、安全性高，redis做了对token和用户信息的管理，用户登录做了缓存。<br>
2）使用ThreadLocal保存用户信息，在请求的线程内可以直接获取用户信息，不需要再次查缓存或者数据库。<br>
3）ThreadLocal使用结束后，做了value的删除，防止了内存泄漏。<br>
4）线程池应用，对于文章浏览数的更新操作，将其加入线程池，异步任务实现浏览数量更新操作，提高页面响应速度，并且保证了线程安全。<br>
5）AOP实现统一缓存处理，以项目首页内容为例，自定义注解Cache，以注解为切入点，第一次访问首页时，将首页信息存储在redis中，再次访问时，直接在redis中获取，无需再次查询。<br>
6）AOP实现统一日志记录，自定义注解LogAnnotation，以注解为切入点，每次接口调用结束后台打印日志。<br>
7）权限系统，通过Security实现认证和授权。<br>
8) 在线聊天系统，登录后进入在线聊天室，通过websocket服务实现在线的聊天。<br>
