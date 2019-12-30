package bbs.component.controller;

import bbs.component.service.interfaces.ArticleService;
import bbs.component.service.interfaces.CommentService;
import bbs.model.Article;
import bbs.model.Comment;
import bbs.model.User;
import bbs.component.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    private final CommentService commentService;

    private final ArticleService articleService;

    public PublicController(UserService userService, CommentService commentService, ArticleService articleService) {
        this.userService = userService;
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/article_details")
    public String showArticleDetails(
            @RequestParam("article_id") int articleId,
            @RequestParam(value = "floor", defaultValue = "0") String floor,
            Model model) {
        Article article = articleService.getArticleById(articleId);
        User author = userService.getUserById(article.getAuthorId());
        model.addAttribute("article", article);
        model.addAttribute("author", author);
        model.addAttribute(floor, "0".equals(floor) ? "楼主" : floor + "楼");
        return "public/article_details";
    }

    @PostMapping("/comment_handler")
    public String commentHandler(
            HttpSession session,
            @RequestParam(value = "comment_to_floor", defaultValue = "0") int commentToFloor,
            @RequestParam("content") String content,
            @RequestParam("article_id") long articleId,
            @RequestHeader(value = "referer", required = false) String referer
    ) {
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setCommentToFloor(commentToFloor);
        comment.setContent(content);
        comment.setCommentByWho(user.getUserName());
        comment.setArticleId(articleId);
        comment.setPublishDate(new Timestamp(System.currentTimeMillis()));
        commentService.insertComment(comment);
        return "redirect:" + referer;
    }

    @GetMapping("/comment")
    public String showComment(
            HttpSession session,
            Model model,
            @RequestParam("article_id") long articleId,
            @RequestParam(value = "comment_to_floor", defaultValue = "0") int commentToFloor
    ) {
        model.addAttribute("title", "查看回复");
        if (session.getAttribute("isLogin") == Boolean.TRUE) {
            model.addAttribute("floor", commentToFloor == 0 ? "楼主" : (commentToFloor + "楼"));
            model.addAttribute("commentToFloor", commentToFloor);
            model.addAttribute("articleId", articleId);
        }
        model.addAttribute("comments", commentService.getCommentByArticleId(articleId));
        return "public/comment";
    }

    @GetMapping("/common/footer")
    public String getFooter() {
        return "public/common/footer";
    }
}
