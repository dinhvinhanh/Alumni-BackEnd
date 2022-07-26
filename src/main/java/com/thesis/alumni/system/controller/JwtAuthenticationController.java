package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.exception.UserHandleException;
import com.thesis.alumni.system.model.JwtRequest;
import com.thesis.alumni.system.model.JwtResponse;
import com.thesis.alumni.system.service.impl.JwtUserDetailsService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class JwtAuthenticationController {
    final AuthenticationManagerBuilder authenticationManagerBuilder;
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws UserHandleException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(), jwtRequest.getPassword()
        );
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (DisabledException e) {
            throw new UserHandleException("This account is not activated!", HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.generateToken(authentication);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

}
