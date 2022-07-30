package com.thesis.alumni.system.exception;

import com.thesis.alumni.system.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalHandleException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<?> jwtTokenException(JwtTokenException ex) {
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> jwtTokenException(AuthenticationException ex) {
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<?> badCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserHandleException.class)
    public ResponseEntity<?> accountNotFoundException(UserHandleException ex) {
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message(ex.getMessage())
                        .status(500)
                        .data(null)
                        .timestamp(new Date())
                        .build(),
                HttpStatus.NOT_ACCEPTABLE);
    }

}
