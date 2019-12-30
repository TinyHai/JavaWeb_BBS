package bbs.component.service.impl;

import bbs.dao.mapper.ArticleMapper;
import bbs.model.Article;
import bbs.component.service.interfaces.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void insertArticle(Article article) {
        articleMapper.insertArticle(article);
    }

    @Override
    public List<Article> getSimpleArticles() {
        return articleMapper.getArticles();
    }

    @Override
    public List<Article> getArticlesByAuthorId(long authorId) {
        return articleMapper.getArticlesByAuthorId(authorId);
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Override
    public long getCommentCountByArticleId(long articleId) {
        return articleMapper.getCommentCountByArticleId(articleId);
    }

    @Override
    public String getContentByArticleId(long articleId) {
        return articleMapper.getContentById(articleId);
    }

    @Override
    public long getCountByAuthorId(long authorId) {
        return articleMapper.getArticleCountByAuthorId(authorId);
    }

    @Override
    public long getAuthorIdByArticleId(long articleId) {
        return articleMapper.getAuthorIdById(articleId);
    }

    @Override
    public void deleteArticleIfExist(long articleId) {
        articleMapper.deleteArticleById(articleId);
    }

    @Override
    public void updateArticleById(long id, String title, String content) {
        articleMapper.updateArticle(id, title, content);
    }
}
