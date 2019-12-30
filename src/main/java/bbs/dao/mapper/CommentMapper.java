package bbs.dao.mapper;

import bbs.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment order by id desc")
    List<Comment> getComments();

    @Select("select * from comment where article_id=#{articleId} order by id desc")
    List<Comment> getCommentsByArticleId(@Param("articleId") long articleId);

    @Insert(
            "insert into comment(article_id, floor, comment_by_who, comment_to_floor, content, publish_date) " +
                    "values(" +
                    "#{articleId}, " +
                    "#{floor}, " +
                    "#{commentByWho}, " +
                    "#{commentToFloor}, " +
                    "#{content}, " +
                    "#{publishDate})"
    )
    void insertComment(Comment comment);

    @Select("select count(*) from comment where comment_by_who=#{userName}")
    long getCommentCountByUserName(@Param("userName") String userName);

    @Select("select * from simple_comment_view where comment_by_who=#{commentByWho}")
    List<Comment> getCommentsByUserName(@Param("commentByWho") String commentByWho);

    @Delete("delete from comment where id=#{id}")
    void deleteCommentById(@Param("id") long id);
}
