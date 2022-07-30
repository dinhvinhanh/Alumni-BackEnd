package com.thesis.alumni.system.service;

import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.User;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface UserService{

    List<User> findAll();

    Optional<User> findByID(String id);

    User saveUser(User user);

    void removeUser(String id);
    void processActiveAccount(UserDto user) throws MessagingException;

    void activeAccount(String token);
}
