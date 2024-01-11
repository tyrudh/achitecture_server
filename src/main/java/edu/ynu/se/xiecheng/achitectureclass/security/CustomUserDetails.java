package edu.ynu.se.xiecheng.achitectureclass.security;

import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public CustomUserDetails(User vUser, Collection<? extends GrantedAuthority> authorities) {
        super(vUser.getUsername(), vUser.getPassword(), authorities);
        this.user = vUser;
    }

    public User getUser() {
        return user;
    }
}
