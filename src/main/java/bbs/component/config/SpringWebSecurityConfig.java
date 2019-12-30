package bbs.component.config;

import bbs.application.Application;
import bbs.auth.AuthUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) Application.getBean("authUserDetailsService");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                AuthUserDetails details = (AuthUserDetails) authentication.getPrincipal();
                session.setAttribute("user", details.asUser());
                session.setAttribute("login_count", 1);
                session.setAttribute("isLogin", true);
                setDefaultTargetUrl("/login/signin_result");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    private String[] whiteListPath = {
            "/", "/login/signup", "/login/signup_handler", "/public/**", "/upload/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(whiteListPath)
                .permitAll()
                .antMatchers("/normal/**", "/upload")
                .hasRole("USER")
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login/signin")
                .usernameParameter("userName")
                .loginProcessingUrl("/login/signin_handler")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/login/signout_now")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/wangEdit/**", "/img/**");
    }
}
