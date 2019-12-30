package bbs.component.controller;

import bbs.component.service.interfaces.ArticleService;
import bbs.component.service.interfaces.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ArticleService articleService;

    private final CommentService commentService;

    public AdminController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/manage")
    public String manageBBS(
            Model model,
            @RequestParam(value = "type", required = false, defaultValue = "0") int type
    ) {
        if (type == 0) {
            model.addAttribute("title", "管理帖子");
            model.addAttribute("articles", articleService.getSimpleArticles());
        } else if (type == 1) {
            model.addAttribute("title", "管理回复");
            model.addAttribute("comments", commentService.getComments());
        }
        model.addAttribute("type", type);

        return "admin/manager_home";
    }

    @GetMapping("/delete_comment")
    public String deleteArticle(
            HttpSession session,
            Model model,
            @RequestParam("comment_id") int commentId,
            @RequestHeader(value = "referer", required = false) String referer
    ) {
        boolean canDelete = (boolean) session.getAttribute("isAdmin");
        if (canDelete) {
            model.addAttribute("title", "删除回复");
            model.addAttribute("opMsg", "删除");
            model.addAttribute("destUrl", "/admin/delete_comment_now?comment_id="
                    + commentId + "&referer=" + referer);
            return "normal/confirm_page";
        }
        return "redirect:" + referer;
    }

    @GetMapping("/delete_comment_now")
    public String deleteArticleHandler(
            HttpSession session,
            @RequestParam("comment_id") int commentId,
            @RequestParam("referer") String referer
    ) {
        boolean canDelete = (boolean) session.getAttribute("isAdmin");
        if (canDelete) {
            commentService.deleteCommentById(commentId);
        } else {
            throw new RuntimeException("你没权限删除该评论");
        }
        return "redirect:" + referer;
    }
}
