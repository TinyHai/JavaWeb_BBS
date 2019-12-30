package bbs.filter;

import bbs.model.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthWiredFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && session.getAttribute("isAdmin") == null) {
            if (user.getRole().equals("ROLE_ADMIN")) {
                session.setAttribute("isAdmin", true);
            } else {
                session.setAttribute("isAdmin", false);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
