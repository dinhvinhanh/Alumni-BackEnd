package com.thesis.alumni.system.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class AuthUtil {
    public static boolean isAdmin() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        AtomicBoolean check = new AtomicBoolean(false);
        authorities.forEach(granted -> {
            if ("ROLE_ADMIN".equals(granted.getAuthority()))
                check.set(true);
        });
        return check.get();
    }
}
