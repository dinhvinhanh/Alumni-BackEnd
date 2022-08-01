package com.thesis.alumni.system.repository;


import com.thesis.alumni.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByIdAndEmail(String id, String email);
    Integer countUserByStatus(String s);
    @Query("select distinct status from User")
    List<String> findAllStatus();

    @Query("select distinct salaryRange from User")
    List<String> findAllSalaryRange();
    Integer countUserBySalaryRange(String range);
}

