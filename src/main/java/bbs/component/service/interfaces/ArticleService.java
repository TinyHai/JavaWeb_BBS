package bbs.component.service.interfaces;

import bbs.model.Article;

import java.util.List;

public interface ArticleService {

    void insertArticle(Article article);

    List<Article> getSimpleArticles();

    List<Article> getArticlesByAuthorId(long authorId);

    Article getArticleById(long articleId);

    long getCommentCountByArticleId(long articleId);

    String getContentByArticleId(long articleId);

    long getCountByAuthorId(long authorId);

    long getAuthorIdByArticleId(long articleId);

    void deleteArticleIfExist(long articleId);

    void updateArticleById(long id, String title, String content);
}
