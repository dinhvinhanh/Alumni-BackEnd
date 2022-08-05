package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.repository.UserRepository;
import com.thesis.alumni.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/surveys")
@AllArgsConstructor
public class SurveyController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveSurvey(@RequestBody UserDto userDto) {
        User user = userService.saveSurvey(userDto);
        return new ResponseEntity<>(BaseResponse
                .builder()
                .message("OK")
                .status(200)
                .data(user)
                .timestamp(new Date())
                .build(), HttpStatus.OK);
    }
}
