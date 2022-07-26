package com.thesis.alumni.system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private Date timestamp;
}
