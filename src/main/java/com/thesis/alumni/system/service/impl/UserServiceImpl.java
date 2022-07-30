package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.exception.JwtTokenException;
import com.thesis.alumni.system.exception.UserHandleException;
import com.thesis.alumni.system.model.Mail;
import com.thesis.alumni.system.repository.UserRepository;
import com.thesis.alumni.system.service.MailService;
import com.thesis.alumni.system.service.UserService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByID(String id) {
        return userRepository.findById(id);
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void processActiveAccount(UserDto userDto) throws MessagingException {
        User user = userRepository.findUserByIdAndEmail(userDto.getId(), userDto.getEmail()).orElseThrow(
                () -> new UserHandleException("Hệ thống chưa có dữ liệu về sinh viên này", HttpStatus.NOT_FOUND));
        String token = jwtTokenUtil.generateToken(user.getEmail());
        Mail mail = mailService.createMailActiveAccount(user.getEmail(), token);
        mailService.sendMail(mail);
    }

    @Override
    public void activeAccount(String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        User user = userRepository.findUserByEmail(email)
                .orElse(null);
        if (user == null)
            throw new UserHandleException("Đường link đã hết hạn hoặc không hợp lệ");
        user.setActive(true);
        userRepository.save(user);
    }

}
