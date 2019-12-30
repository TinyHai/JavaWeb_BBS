package bbs.component.controller;

import bbs.exceptions.EmailExistException;
import bbs.exceptions.UserExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler({UserExistException.class, EmailExistException.class})
    public ModelAndView signUpException(RuntimeException ex) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login/signup");
        model.addObject("title", "注册");
        model.addObject("error", true);
        if (ex instanceof UserExistException) {
            model.addObject("errorMsg", "用户名已被注册");
        } else if (ex instanceof EmailExistException) {
            model.addObject("errorMsg", "该邮箱已被注册");
        }
        return model;
    }
}
