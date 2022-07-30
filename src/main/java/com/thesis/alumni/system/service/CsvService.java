package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.repository.UserRepository;
import com.thesis.alumni.system.utils.CsvUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvService {
    final UserRepository userRepository;
    public void save(MultipartFile file) {
        try {
            List<User> tutorials = CsvUtil.csvToUser(file.getInputStream());
            userRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}