package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.model.Mail;
import com.thesis.alumni.system.service.MailService;
import com.thesis.alumni.system.service.UserService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @GetMapping("")
    public ResponseEntity<?> findAll(
                @RequestParam(defaultValue = "0") Integer page,
                @RequestParam(defaultValue = "20") Integer limit
            ){
        Page<User> result = userService.findAll(page, limit);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    };

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "20") String name
    ){
        Page<User> result = userService.findUserLikeName(name, page, limit);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    };

    @PostMapping("")
    public ResponseEntity<?> createUserByAdmin(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(user)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.removeUser(id);

        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto user){
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAlumni(@RequestBody UserDto userDto, @PathVariable String id){
        User user = userService.updateUser(id, userDto);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(user)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getMyProfile(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(user)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }
}
