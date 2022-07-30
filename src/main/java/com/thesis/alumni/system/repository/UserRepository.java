package com.thesis.alumni.system.repository;


import com.thesis.alumni.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByIdAndEmail(String id, String email);
}

