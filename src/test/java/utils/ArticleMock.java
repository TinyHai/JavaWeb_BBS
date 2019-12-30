package utils;

import bbs.model.Article;
import bbs.model.Comment;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ArticleMock {
    public static List<Article> getArticles(int size) {
        List<Article> articles = new ArrayList<>(size);
        Article article;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < size; ++i) {
            article = new Article();
            article.setTitle("这是Article" + i);
            article.setContent("这是Content" + i);
            article.setAuthorId(3);
            article.setPublishDate(timestamp);
            articles.add(article);
        }
        return articles;
    }

    public static List<Comment> getCommentFromArticleId(long id) {
        List<Comment> comments = new ArrayList<>();
        Comment comment;
        for (int i = 0; i < 5; ++i) {
            comment = new Comment();
            comment.setArticleId(id);
            comment.setFloor(i);
            comment.setCommentByWho("tiny");
            comments.add(comment);
        }
        return comments;
    }
}
