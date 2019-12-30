package bbs.component.service.interfaces;

import bbs.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();

    List<Comment> getCommentByArticleId(long articleId);

    void insertComment(Comment comment);

    long getCountByUserName(String userName);

    List<Comment> getAllCommentsByUserName(String userName);

    void deleteCommentById(long id);
}
