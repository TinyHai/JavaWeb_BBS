package bbs.auth;

import bbs.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthUserDetails implements UserDetails {

    private final User user;

    private final Collection<GrantedAuthority> authorities;

    public AuthUserDetails(User user, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return 1 != user.getPwdChanged();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User asUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuthUserDetails{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }
}
