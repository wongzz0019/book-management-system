package com.webtest.dao;

import com.webtest.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //根据创建时间倒序来排 
    List<Comment> findByParentIdNull(@Param("ParentId") Long ParentId);

    //查询一级回复
    List<Comment> findByParentIdNotNull( @Param("id") Long id);

    //查询二级回复
    List<Comment> findByReplayId(@Param("childId") Long childId);

    //查询父级对象
    Comment findByParentCommentId(Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);
}
