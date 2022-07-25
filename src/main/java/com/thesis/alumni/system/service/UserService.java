package com.thesis.alumni.system.service;

import com.thesis.alumni.system.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{

    List<User> findAll();

    Optional<User> findByID(Long id);

    User saveUser(User user);

    void removeUser(Long id);

}
