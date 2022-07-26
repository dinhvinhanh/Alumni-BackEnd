package com.thesis.alumni.system.filter;

import com.thesis.alumni.system.service.impl.JwtUserDetailsService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null) {
            Cookie token = WebUtils.getCookie(request, "token");
            if (token != null)
                requestTokenHeader = "Bearer " + token.getValue();
        }

        String username = null;
        boolean expire = true;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                expire = jwtTokenUtil.isTokenExpired(jwtToken);
            } catch (Exception e) {
                logger.warn("Unable to get JWT Token!");
            }
        }

        if (username != null && !expire && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = jwtTokenUtil.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
