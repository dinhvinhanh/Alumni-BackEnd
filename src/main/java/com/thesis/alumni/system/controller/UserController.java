package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.model.User;
import com.thesis.alumni.system.repository.UserRepository;
import com.thesis.alumni.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> findAll(){
        return userService.findAll();
    };

    @PostMapping("/user")
    public User createEmployee(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User user = userService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        userService.removeUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable  long id){
        User user = userService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        return ResponseEntity.ok(user);
    }
}
