package bbs.component.service.impl;

import bbs.dao.mapper.CommentMapper;
import bbs.model.Comment;
import bbs.component.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> getComments() {
        return commentMapper.getComments();
    }

    @Override
    public List<Comment> getCommentByArticleId(long articleId) {
        return commentMapper.getCommentsByArticleId(articleId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public long getCountByUserName(String userName) {
        return commentMapper.getCommentCountByUserName(userName);
    }

    @Override
    public List<Comment> getAllCommentsByUserName(String userName) {
        return commentMapper.getCommentsByUserName(userName);
    }

    @Override
    public void deleteCommentById(long id) {
        commentMapper.deleteCommentById(id);
    }
}
