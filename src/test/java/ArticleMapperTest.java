import bbs.dao.mapper.ArticleMapper;
import bbs.dao.mapper.CommentMapper;
import bbs.model.Article;
import bbs.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utils.ArticleMock;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
@WebAppConfiguration
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void test() {
        Assert.notNull(commentMapper.getCommentsByArticleId(1), "getCommentsByArticleId return null");
        Assert.notEmpty(commentMapper.getCommentsByArticleId(1), "getCommentsByArticleId return empty null");
        Assert.notNull(articleMapper.getArticlesByAuthorId(3), "getArticlesByAuthorId return null");
        Assert.notEmpty(articleMapper.getArticlesByAuthorId(3), "getArticlesByAuthorId return empty list");
        Assert.notNull(articleMapper.getArticles(), "getArticles return null");
        Assert.notEmpty(articleMapper.getArticles(), "getArticles return empty list");
        Article article = articleMapper.getArticleById(1);
        System.out.println(article);
        List<Comment> comments = article.getComments();
        System.out.println(comments);
    }
}
