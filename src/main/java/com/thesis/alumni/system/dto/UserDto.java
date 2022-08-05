package com.thesis.alumni.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String email;
    private String password;
    private String avatar;
    private String name;
    private String jobHistory;
    private String workplace;
    private Boolean active;
    private String job;
    private String gender;
    private String birthPlace;
    private String salaryRange;
    private String status;
    private Date dateOfBirth;
    private String className;
    private String satisfactionLevel;
    private String language;
    private String feedback;
}
