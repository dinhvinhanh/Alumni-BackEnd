package com.thesis.alumni.system.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus
public class JwtTokenException extends RuntimeException{
    public JwtTokenException(String message) {
        super(message);
    }
}