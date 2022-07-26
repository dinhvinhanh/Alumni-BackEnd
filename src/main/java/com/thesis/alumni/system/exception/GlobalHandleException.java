package com.thesis.alumni.system.exception;

import com.thesis.alumni.system.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
