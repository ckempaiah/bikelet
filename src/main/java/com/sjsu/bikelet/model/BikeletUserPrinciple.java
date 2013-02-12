package com.sjsu.bikelet.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import sun.security.util.BigInt;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ckempaiah
 * Date: 2/9/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BikeletUserPrinciple extends User {
    private Long userId;

    public BikeletUserPrinciple(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
