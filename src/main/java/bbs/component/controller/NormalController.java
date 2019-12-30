package bbs.component.controller;

import bbs.annotation.FormToken;
import bbs.component.service.interfaces.CommentService;
import bbs.component.service.interfaces.UserService;
import bbs.model.Article;
import bbs.model.User;
import bbs.component.service.interfaces.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@RequestMapping("/normal")
public class NormalController {

    private final ArticleService articleService;

    private final CommentService commentService;

    private final UserService userService;

    public NormalController(ArticleService articleService, CommentService commentService, UserService userService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @FormToken(save = true)
    @GetMapping("/new_article")
    public String newArticle(Model model) {
        model.addAttribute("title", "发布新帖");
        return "normal/new_article";
    }

    @PostMapping("/pub_article")
    @FormToken(remove = true)
    public String pubArticle(
            HttpSession session,
            Model model,
            @RequestParam("article_title") String title,
            @RequestParam("article_content") String content) {
        User user = (User) session.getAttribute("user");
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthorId(user.getId());
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        articleService.insertArticle(article);
        model.addAttribute("title", "发帖结果");
        model.addAttribute("opResult", "发布成功");
        return "normal/op_result";
    }

    @GetMapping("/user_page")
    public String showUserPage(
            HttpSession session,
            Model model,
            @RequestParam(value = "user_name", required = false) String userName
    ) {

        User self = (User) session.getAttribute("user");
        User pageUser;
        self = userService.getUserByName(self.getUserName());
        if (userName == null) {
            pageUser = userService.getUserByName(self.getUserName());
        } else {
            pageUser = userService.getUserByName(userName);
        }
        model.addAttribute("title", pageUser.getUserName() + "的个人中心");
        if (self.equals(pageUser)) {
            model.addAttribute("who", "我");
        } else {
            model.addAttribute("who", "他");
        }
        model.addAttribute("pageUser", pageUser);
        model.addAttribute("authority", pageUser.getRole().equals("ROLE_ADMIN") ? "管理员" : "普通用户");
        model.addAttribute("commentCount", commentService.getCountByUserName(pageUser.getUserName()));
        model.addAttribute("articleCount", articleService.getCountByAuthorId(pageUser.getId()));
        return "normal/user_page";
    }

    @GetMapping("/someone_all_articles")
    public String showSomeoneAllArticles(
            HttpSession session,
            Model model,
            @RequestParam("user_name") String userName
    ) {
        User user = (User) session.getAttribute("user");
        if (user.getUserName().equals(userName)) {
            model.addAttribute("isSelf", true);
        }
        User pageUser = userService.getUserByName(userName);
        model.addAttribute("title", userName + "的帖子");
        model.addAttribute("pageUser", pageUser);
        model.addAttribute("articles", articleService.getArticlesByAuthorId(pageUser.getId()));
        return "normal/someone_all_articles";
    }

    @GetMapping("/someone_all_comments")
    public String showSomeoneAllComments(
            HttpSession session,
            Model model,
            @RequestParam("user_name") String userName
    ) {
        User pageUser = userService.getUserByName(userName);
        model.addAttribute("title", userName + "的回复");
        model.addAttribute("pageUser", pageUser);
        model.addAttribute("comments", commentService.getAllCommentsByUserName(pageUser.getUserName()));
        return "normal/someone_all_comments";
    }


    @GetMapping("/delete_article")
    public String deleteArticle(
            HttpSession session,
            Model model,
            @RequestParam("article_id") int articleId,
            @RequestHeader(value = "referer", required = false) String referer
    ) {
        boolean canDelete = false;
        canDelete = (boolean) session.getAttribute("isAdmin")
                || articleService.getAuthorIdByArticleId(articleId) == ((User) session.getAttribute("user")).getId();
        if (canDelete) {
            model.addAttribute("title", "删除文章");
            model.addAttribute("opMsg", "删除");
            model.addAttribute("destUrl", "/normal/delete_article_now?article_id="
                    + articleId + "&referer=" + referer);
            return "normal/confirm_page";
        }
        return "redirect:" + referer;
    }

    @GetMapping("/delete_article_now")
    public String deleteArticleHandler(
            HttpSession session,
            @RequestParam("article_id") int articleId,
            @RequestParam("referer") String referer
    ) {
        boolean canDelete = false;
        canDelete = (boolean) session.getAttribute("isAdmin")
                || articleService.getAuthorIdByArticleId(articleId) == ((User) session.getAttribute("user")).getId();
        if (canDelete) {
            articleService.deleteArticleIfExist(articleId);
        } else {
            throw new RuntimeException("你没权限删除该帖子");
        }
        return "redirect:" + referer;
    }

    @GetMapping("/modify_article")
    public String modifyArticle(
            HttpSession session,
            Model model,
            @RequestParam("article_id") int articleId
    ) {
        User self = (User) session.getAttribute("user");
        System.out.println(self);
        long authorId = articleService.getAuthorIdByArticleId(articleId);
        if (self.getId() != authorId) {
            throw new UnsupportedOperationException("你不能修改别人的帖子");
        }
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("article", article);
        return "normal/modify_article";
    }

    @PostMapping("/modify_article_handler")
    public String modifyArticleHandler(
            HttpSession session,
            @RequestParam("article_id") int articleId,
            @RequestParam("article_title") String title,
            @RequestParam("article_content") String content,
            Model model
    ) {
        User self = (User) session.getAttribute("user");
        long authorId = articleService.getAuthorIdByArticleId(articleId);
        model.addAttribute("title", "修改结果");
        if (self.getId() != authorId) {
            model.addAttribute("opResult", "修改失败， 你不能修改别人的帖子！");
        } else {
            articleService.updateArticleById(articleId, title, content);
            model.addAttribute("opResult", "修改成功");
        }
        return "normal/op_result";
    }
}
