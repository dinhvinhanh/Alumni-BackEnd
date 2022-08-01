package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticController {
    private final UserService userService;

    @GetMapping("/alumni/status")
    public ResponseEntity<?> countAlumniStatus() {
        Map<String, Integer> result = userService.statisticStatus();
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/alumni/salary")
    public ResponseEntity<?> countAlumniSalaryRange() {
        Map<String, Integer> result = userService.statisticSalaryRange();
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }
}
