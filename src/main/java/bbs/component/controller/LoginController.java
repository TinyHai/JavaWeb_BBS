package bbs.component.controller;

import bbs.annotation.FormToken;
import bbs.component.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signIn(
            HttpSession session,
            Model model,
            @RequestParam(value = "error", required = false) String error
    ) {
        if (session.getAttribute("login_count") != null) {
            return "redirect:/login/signin_result";
        }
        model.addAttribute("title", "登录");
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "login/signin";
    }

    @GetMapping("/signout")
    public String signOut(Model model) {
        model.addAttribute("title", "退出登录");
        model.addAttribute("opMsg", "注销");
        model.addAttribute("destUrl", "/login/signout_now");
        return "normal/confirm_page";
    }

    @FormToken(save = true)
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("title", "注册");
        model.addAttribute("error", false);
        return "login/signup";
    }

    @FormToken(remove = true)
    @PostMapping("/signup_handler")
    public String signUpHandler(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model
    ) {
        if (userName == null || password == null || email == null) {
            throw new IllegalArgumentException("some args is null");
        }
        userService.insertUserIfAbsent(userName, password, email);
        model.addAttribute("title", "注册结果");
        return "login/signup_result";
    }

    @RequestMapping(value = "/signin_result", method = RequestMethod.GET)
    public String signInHandler(
            HttpSession session,
            Model model
    ) {
        boolean fromLoginPage = false;
        Integer loginCount = (Integer) session.getAttribute("login_count");

        if (loginCount != null && --loginCount == 0) {
            fromLoginPage = true;
        }
        session.setAttribute("login_count", loginCount);

        model.addAttribute("title", "登录结果");
        model.addAttribute("fromLoginPage", fromLoginPage);

        return "login/signin_result";
    }
}
