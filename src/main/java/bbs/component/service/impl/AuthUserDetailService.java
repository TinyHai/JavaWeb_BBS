package bbs.component.service.impl;

import bbs.auth.AuthUserDetails;
import bbs.model.User;
import bbs.component.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("authUserDetailsService")
public class AuthUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailService.class);

    private final UserService userService;

    public AuthUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getAccountByName(username);
            if (user != null) {
                logger.debug(user.toString());
                List<GrantedAuthority> authorities = getAuthorities(user.getRole());
                return new AuthUserDetails(user, authorities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private final List<String> roles = new ArrayList<>();

    {
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        int level = roles.indexOf(role) + 1;
        List<String> hasRoles = roles.subList(0, level);
        for (String roleName : hasRoles) {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        return authorities;
    }
}
