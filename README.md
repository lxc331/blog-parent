# 工程简介 
基于springboot + vue + websocket 的前后端分离可交流博客平台

# 1. 工程搭建
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


**注意换掉里面的mysql端口，redis端口，三个后端模块有不同的端口都要换(blog-admin,blog-app,blog-chat)，加上一个前端(blog-api)模块的跨域配置端口也要换**
