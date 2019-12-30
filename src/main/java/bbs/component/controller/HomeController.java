package bbs.component.controller;

import bbs.model.Article;
import bbs.component.service.interfaces.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/", "/index"})
    public String home(HttpSession session, Model model) {
        List<Article> articles = articleService.getSimpleArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("title", "主页");
        return "index";
    }
}
