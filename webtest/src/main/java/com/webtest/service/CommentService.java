package com.webtest.service;

import com.webtest.entity.Comment;

import java.util.List;

public interface CommentService {

    //评论的列表
    List<Comment> listComment();

    //新增评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);
}
