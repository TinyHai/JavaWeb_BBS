package bbs.dao.mapper;

import bbs.dao.convert.BlobStringConvert;
import bbs.model.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from article_view order by id desc")
    @Results(value = {
            @Result(
                    id = true,
                    property = "id",
                    column = "id"
            ),
            @Result(
                    property = "comments",
                    column = "id",
                    many = @Many(
                            select = "bbs.dao.mapper.CommentMapper.getCommentsByArticleId",
                            fetchType = FetchType.LAZY
                    )
            ),
            @Result(
                    property = "content",
                    column = "content",
                    typeHandler = BlobStringConvert.class
            )
    })
    List<Article> getArticles();

    @Insert(
            "insert into article(title, content, publish_date, author_id) " +
                    "values(#{title}, #{content}, #{publishDate}, #{authorId})"
    )
    void insertArticle(Article article);

    @Select("select * from article where author_id=#{authorId} order by id desc")
    @Results(value = {
            @Result(
                    id = true,
                    property = "id",
                    column = "id"
            ),
            @Result(
                    property = "comments",
                    column = "id",
                    many = @Many(
                            select = "bbs.dao.mapper.CommentMapper.getCommentsByArticleId",
                            fetchType = FetchType.LAZY
                    )
            ),
            @Result(
                    property = "content",
                    column = "content",
                    typeHandler = BlobStringConvert.class
            )
    })
    List<Article> getArticlesByAuthorId(@Param("authorId") long authorId);

    @Select("select * from article where id=#{id}")
    @Results(value = {
            @Result(
                    id = true,
                    property = "id",
                    column = "id"
            ),
            @Result(
                    property = "comments",
                    column = "id",
                    many = @Many(
                            select = "bbs.dao.mapper.CommentMapper.getCommentsByArticleId",
                            fetchType = FetchType.LAZY
                    )
            ),
            @Result(
                    property = "content",
                    column = "content",
                    typeHandler = BlobStringConvert.class
            )
    })
    Article getArticleById(@Param("id") long id);

    @Select("select comment_count from article where id=#{articleId}")
    long getCommentCountByArticleId(@Param("articleId") long articleId);

    @Select("select content from article where id=#{id}")
    @Results(value = {
            @Result(
                    property = "content",
                    column = "content",
                    typeHandler = BlobStringConvert.class
            )
    })
    String getContentById(@Param("id") long id);

    @Select("select count(*) from article where author_id=#{authorId}")
    long getArticleCountByAuthorId(@Param("authorId") long authorId);

    @Select("select author_id from article where id=#{id}")
    long getAuthorIdById(@Param("id") long id);

    @Delete("delete from article where id=#{id}")
    void deleteArticleById(@Param("id") long id);

    @Update("update article set title=#{title}, content=#{content} where id=#{id}")
    void updateArticle(
            @Param("id") long id,
            @Param("title") String title,
            @Param("content") String content
    );
}
