package com.webtest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private Long id;
    private String nickname;
    private String content;
    private Date createTime;

    //父级id
    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private boolean adminComment;

    public Comment() {
    }

    public Comment(Long id, String nickname, String content, Date createTime, Long parentCommentId, String parentNickname, List<Comment> replyComments, Comment parentComment, boolean adminComment) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.createTime = createTime;
        this.parentCommentId = parentCommentId;
        this.parentNickname = parentNickname;
        this.replyComments = replyComments;
        this.parentComment = parentComment;
        this.adminComment = adminComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", parentCommentId=" + parentCommentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                ", adminComment=" + adminComment +
                '}';
    }
}
