# Spring-websocket
  传统的HTTP协议，一般通过向服务器发送请求，拉取数据实现半双工通信，缺点是服务器难以直接向浏览器下发消息，因此，websocket协议应运而生，可用于向建立连接的浏览器主动下发任意数据（PUSH）。本项目基于Spring平台，整合websocket协议，实现一个简易web聊天室的功能。主要特性如下:
  
  1.包含聊天室登录、退出的功能。登录时，浏览器自动向服务器发起websocket连接，退出时自动切断。登录页面为http://localhost:8890/login
  
  2.账号Jon或者TOM，密码是1234，登录后，用户可查看到聊天室在线的用户列表，我们在服务器上通过一个hashmap始终记录了当前在线的用户列表；

  3.登录的用户可以点击一个在线的其他用户，并给他发送消息，消息先提交给服务器，在通过服务器转发给另一端用户；
  
  4.支持群发消息的功能，使用时，服务器会将收到的消息群发给当前在线的所有用户;
  
  5.添加好友上线提醒和下线提醒的功能，当有好友上线或下线时自动通知所有其他在线人，不要刷新页面可看到实时在线用户列表。
  
  效果图：
![输入图片说明](https://images.gitee.com/uploads/images/2018/1212/100350_2a6aeccf_1110335.gif "SSM.gif")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/155000_fbd7a93b_1110335.jpeg "在这里输入图片标题")
   
![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/155008_ad2d6e7a_1110335.jpeg "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/155016_df4cf908_1110335.jpeg "在这里输入图片标题")

![输入图片说明](http://git.oschina.net/uploads/images/2016/1121/155029_5e3afabc_1110335.jpeg "在这里输入图片标题")

### 著作权
#### 匠心巨制，震撼上市
![输入图片说明](https://gitee.com/shenzhanwang/Spring-elastic_search/raw/master/pic/mmexport1680185330486.jpg)

|购买地址|
|--|
|![输入图片说明](https://gitee.com/shenzhanwang/RuoYi-activiti/raw/ruoyi-activiti/pic/%E8%B4%AD%E4%B9%B0.jpg)|

### 专利文章
|   | 标题 | 技术领域 |
|---|----|------|
| 1 |  [一种基于微服务架构的车联网大数据分析系统](https://mp.weixin.qq.com/s?__biz=Mzg5MjY3OTk0OQ==&mid=2247484096&idx=1&sn=11677bc50cd18e448135319394238e80&chksm=c03b20a2f74ca9b41c7d1c1e60e1407e1530a98b86498822f387da26e551826f8136663b45a0#rd)  |  微服务|
| 2 |  [一种流式数据场景下Elasticsearch索引的自动化扩容方法](https://mp.weixin.qq.com/s?__biz=Mzg5MjY3OTk0OQ==&mid=2247484167&idx=1&sn=94587b5e196bbda2d6cc4b976b03d0c7&chksm=c03b2165f74ca873b77380a23c4e06b71adf6ffc40634440f467fb71c20aa4cc2fe72a5d65a9#rd)  |  搜索引擎    |
| 3 |  [大数据钻取分析方法、装置、设备及存储介质](https://mp.weixin.qq.com/s?__biz=Mzg5MjY3OTk0OQ==&mid=2247484385&idx=1&sn=6fe4a4ac34badd91db39b4cc042a3f18&chksm=c03b2183f74ca89508333f3d5c8330aa32248eda86e4cf7657a5a293e1bf9d49888fe78b1072#rd)  |  大数据分析    |
| 4 |  [一种基于工作流引擎的自动化办公方法和系统](https://mp.weixin.qq.com/s?__biz=Mzg5MjY3OTk0OQ==&mid=2247484895&idx=1&sn=1094f0b9abef603e8126dea68db75cca&chksm=c03b27bdf74caeab50d56ac110b9ab0d36bc9b9069e311083a5c4295ab03ee4b0595a00a3ba2#rd)  |  工作流引擎    |



### 视频教程

|<div align=left><a href="https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg5MjY3OTk0OQ==&action=getalbum&album_id=2862983682029043714#wechat_redirect" target="_blank">Elasticsearch数据搜索与分析实战演练</a></div> |
|--|
|<div align=left><a href="https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg5MjY3OTk0OQ==&action=getalbum&album_id=2790222720155451398#wechat_redirect" target="_blank">RuoYi-activiti开发指南</a></div> |


### 附录：中央技术储备仓库（Central Technique Reserve Repository）

#### 基础篇:职业化，从做好OA系统开始
1. [Spring boot整合Mybatis实现增删改查（支持多数据源）](https://gitee.com/shenzhanwang/SSM)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
2. [Spring,SpringMVC和Hibernate的整合实现增删改查](https://gitee.com/shenzhanwang/SSH)
3. [Spring boot整合activiti工作流引擎实现OA开发](https://gitee.com/shenzhanwang/Spring-activiti)![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg)   
4. [Ruoyi-boot集成工作流引擎Flowable实例](https://gitee.com/shenzhanwang/Ruoyi-flowable)![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg) 
5. [Spring发布与调用REST风格的WebService](https://gitee.com/shenzhanwang/Spring-REST)
6. [Spring boot整合Axis调用SOAP风格的web服务](https://gitee.com/shenzhanwang/Spring-axis)
7. [Spring boot整合Apache Shiro实现RBAC权限控制](https://gitee.com/shenzhanwang/Spring-shiro)
8. [使用Spring security实现RBAC权限控制](https://gitee.com/shenzhanwang/spring-security-demo)
#### 中级篇：中间件的各种姿势
9. [Spring boot整合mongoDB文档数据库实现增删改查](https://gitee.com/shenzhanwang/Spring-mongoDB)
10. [Spring连接Redis实现缓存](https://gitee.com/shenzhanwang/Spring-redis)
11. [Spring连接图存数据库Neo4j实现增删改查](https://gitee.com/shenzhanwang/Spring-neo4j)
12. Spring boot整合列存数据库hbase实现增删改查
13. [Spring平台整合消息队列ActiveMQ实现发布订阅、生产者消费者模型（JMS）](https://gitee.com/shenzhanwang/Spring-activeMQ)
14. [Spring boot整合消息队列RabbitMQ实现四种消息模式（AMQP）](https://gitee.com/shenzhanwang/Spring-rabbitMQ)
15. Spring boot整合kafka 2.1.0实现大数据消息管道
16. [Spring boot整合websocket实现即时通讯](https://gitee.com/shenzhanwang/Spring-websocket)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
17. [Spring security整合oauth2实现token认证](https://gitee.com/shenzhanwang/Spring-security-oauth2)
18. [Spring boot整合MinIO客户端实现文件管理](https://gitee.com/shenzhanwang/Spring-minio)
19. 23种设计模式，源码、注释、使用场景 
20. [使用ETL工具Kettle的实例](https://gitee.com/shenzhanwang/Kettle-demo)
21. Git指南和分支管理策略 
22. 使用数据仓库进行OLAP数据分析（Mysql+Kettle+Zeppelin）
#### 高级篇：分布式系统和大数据开发
23. [zookeeper原理、架构、使用场景和可视化](https://gitee.com/shenzhanwang/zookeeper-practice)
24. Spring boot整合Apache dubbo v2.7.5实现分布式服务治理（SOA架构） ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题") 
>  包含组件Spring boot v2.2.2+Dubbo v2.7.5+Nacos v1.1.1
<a href="https://images.gitee.com/uploads/images/2020/0114/084731_fd0b7a82_1110335.gif" target="_blank">效果图</a>
25. 使用Spring Cloud Alibaba v2.2.7实现微服务架构（MSA架构）![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg)   
>  包含组件Nacos+Feign+Gateway+Ribbon+Sentinel+Zipkin
<a href="https://images.gitee.com/uploads/images/2020/0106/201827_ac61db63_1110335.gif" target="_blank">效果图</a>
26. 使用jenkins+centos+git+maven搭建持续集成环境自动化部署分布式服务 
27. 使用docker+compose+jenkins+gitlab+spring cloud实现微服务的编排、持续集成和动态扩容 
28. 使用Spark进行分布式计算
- Spark SQL做离线计算
- Spark Streaming做实时计算
- Structured Streaming做实时计算
29. 使用Flink实现流批一体化的分布式计算
30. 搭建高可用nginx集群和Tomcat负载均衡 
31. 使用mycat实现Mysql数据库的主从复制、读写分离、分表分库、负载均衡和高可用 
32. [《Elasticsearch数据搜索与分析实战》源码](https://gitee.com/shenzhanwang/Spring-elastic_search) ![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg "在这里输入图片标题")
#### 特别篇：分布式事务和并发控制
33. 基于可靠消息最终一致性实现分布式事务（activeMQ）
34. Spring boot dubbo整合seata实现分布式事务![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
> 包含组件nacos v2.0.2 + seata v1.4.2 +spring boot dubbo v2.7.5
<a href="https://images.gitee.com/uploads/images/2020/0119/112233_62a33a77_1110335.gif" target="_blank">效果图</a>
35. Spring cloud alibaba v2.2.7整合seata实现分布式事务 ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
> 包含组件nacos v2.0.2 + seata v1.4.2 +spring cloud alibaba v2.2.7
<a href="https://images.gitee.com/uploads/images/2020/0119/134408_ee14a016_1110335.gif" target="_blank">效果图</a>
36. 并发控制：数据库锁机制和事务隔离级别的实现![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题") 
37. 并发控制：使用redission实现分布式锁 
38. 并发控制：使用zookeeper实现分布式锁 
39. 并发控制：Java多线程编程实例
40. 并发控制：使用netty实现高性能NIO通信 

### 关注微信公众号获取更多技术文章和源码

![输入图片说明](https://gitee.com/shenzhanwang/Spring-elastic_search/raw/master/pic/qrcode_for_gh_4c2318bb0f7f_258.jpg)
