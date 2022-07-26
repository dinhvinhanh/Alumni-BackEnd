package com.thesis.alumni.system.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus
public class UserHandleException extends RuntimeException{
    private UserHandleException() {
    }
    private HttpStatus status;
    public UserHandleException(String message) {
        super(message);
    }

    public UserHandleException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
