package com.webtest.service.impl;

import com.webtest.dao.CommentMapper;
import com.webtest.entity.Comment;
import com.webtest.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listComment() {
        //获取所有一级评论,没有父节点的默认为-1,Long.parseLong("-1")
        List<Comment> comments = commentMapper.findByParentIdNull(Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            //回复的评论
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
//            查询出子评论
            combineChildren(childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return eachComment(comments);
    }

//    循环每个顶级的评论节点
    private List<Comment> eachComment(List<Comment> comments) {
//        新建一个集合
        List<Comment> commentsView = new ArrayList<>();
//        把数据循环copy到新集合
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

//     @param comments root根节点，blog不为空的对象集合
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    private void combineChildren(List<Comment> childComments, String parentNickname1) {
//      判断是否有一级子评论
        if(childComments.size() > 0){
//          循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//              查询出子二级评论
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }

    private void recursively(Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByReplayId(childId);
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(replayId,parentNickname);
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        //判断有没有在别人的评论上进行评论还是一个新的评论
        Long parentCommentId = comment.getParentCommentId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论。拿到父级评论对象，再set到子级评论
            comment.setParentComment(commentMapper.findByParentCommentId(comment.getParentCommentId()));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }
}
