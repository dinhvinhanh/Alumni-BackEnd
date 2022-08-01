package com.thesis.alumni.system.service;

import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.User;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface UserService{

    Page<User> findAll(Integer page, Integer limit);

    Optional<User> findByID(String id);

    User saveUser(User user);

    void removeUser(String id);
    void processActiveAccount(UserDto user) throws MessagingException;

    void activeAccount(String token);

    Map<String, Integer> statisticStatus();

    Map<String, Integer> statisticSalaryRange();
}
