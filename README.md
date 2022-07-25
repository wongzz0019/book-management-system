# 图书借阅系统

#### 介绍
使用 SpringBoot 开发的图书借阅系统

### 功能
用户：登录、注册、借阅、归还、搜索、评论
管理员：管理图书、管理用户、管理评论

### 功能特色
•	使用Redis作缓存存储登录验证码
•	使用ElasticSearch对图书搜索功能进行改进，从原本的数据库模糊查询改进为ElasticSearch多词、关键词搜索，提升了用户搜索图书体验感
•	评论敏感词过滤采用前缀树Trie实现
•	使用SpringSecurity安全框架区分管理员和用户的权限，还对密码进行了加密
•	通过Thymeleaf模板引擎获取后台数据并展示在前端页面上

#### 软件架构
前端：Semantic UI + Thymeleaf
后端：Springboot + Mybatis + Spring Security + Elasticsearch + Redis
数据库：MySQL 5.7

