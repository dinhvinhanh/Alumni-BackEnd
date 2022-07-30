package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.model.Mail;
import com.thesis.alumni.system.service.MailService;
import com.thesis.alumni.system.service.UserService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @GetMapping("")
    public List<User> findAll(){
        return userService.findAll();
    };

    @PostMapping("")
    public User createUserByAdmin(@RequestBody User user) throws MessagingException {
        Mail mail = mailService.createMailActiveAccount("nguyenhuuvuno1@gmail.com", "1 gio");
        mailService.sendMail(mail);
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){

        User user = userService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        userService.removeUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        return ResponseEntity.ok(user);
    }
}
