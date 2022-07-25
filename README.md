# 图书借阅系统

### 介绍
使用 SpringBoot 开发的图书借阅系统

### 功能
用户：登录、注册、借阅、归还、搜索、评论
管理员：管理图书、管理用户、管理评论

### 功能特色
1. 注册的账号需要通过邮件激活，使用 SpringEmail 实现
2. 登录验证码缓存在 Redis 中
3. ElasticSearch实现对图书全局搜索，相比数据库的模糊查询，提升了搜索体验感与搜索速度
4. 过滤评论区敏感字、词
5. SpringSecurity安全框架区分用户权限、用户密码加密

#### 软件架构
前端：Semantic UI + Thymeleaf
后端：Springboot + Mybatis + Spring Security + Elasticsearch + Redis
数据库：MySQL 5.7

