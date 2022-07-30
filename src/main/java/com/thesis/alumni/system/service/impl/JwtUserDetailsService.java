package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.exception.UserHandleException;
import com.thesis.alumni.system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // login with email or username
        com.thesis.alumni.system.entity.User user = userRepository.findUserByEmail(s)
                .orElseThrow(() -> new UserHandleException("Tài khoản này chưa được kích hoạt hoặc không tồn tại trong hệ thống!"));
        if (user == null)
            throw new UsernameNotFoundException("Login false: " + s);
        List<GrantedAuthority> granted =  user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new User(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, granted);
    }
}
