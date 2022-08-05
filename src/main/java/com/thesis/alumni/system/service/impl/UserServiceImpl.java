package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.dto.UserDto;
import com.thesis.alumni.system.entity.Role;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.exception.UserHandleException;
import com.thesis.alumni.system.model.Mail;
import com.thesis.alumni.system.repository.RoleRepository;
import com.thesis.alumni.system.repository.UserRepository;
import com.thesis.alumni.system.service.MailService;
import com.thesis.alumni.system.service.UserService;
import com.thesis.alumni.system.utils.JwtTokenUtil;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    public final ModelMapper modelMapper;

    @Override
    public Page<User> findAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("name").ascending());
        return userRepository.findAll(pageable);
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
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public void processActiveAccount(UserDto userDto) throws MessagingException {
        User user = userRepository.findUserByIdAndEmail(userDto.getId(), userDto.getEmail())
                .orElseThrow(() -> new UserHandleException("Hệ thống chưa có dữ liệu về sinh viên này", HttpStatus.NOT_FOUND));
        String token = jwtTokenUtil.generateToken(user.getEmail());
        Mail mail = mailService.createMailActiveAccount(user.getEmail(), token);
        mailService.sendMail(mail);
    }

    @Override
    public void activeAccount(String token, String password) {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        User user = userRepository.findUserByEmail(email)
                .orElse(null);
        if (user == null)
            throw new UserHandleException("Đường link đã hết hạn hoặc không hợp lệ");

        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        Role roleUser = roleRepository.findFirstByName("ROLE_USER");
        user.setRoles(Stream.of(roleUser).collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public Map<String, Integer> statisticStatus() {
        List<String> status = userRepository.findAllStatus();
        Map<String, Integer> result = new HashMap<>();
        for (String s: status) {
            Integer count = userRepository.countUserByStatus(s);
            result.put(s, count);
        }
        return result;
    }

    @Override
    public Map<String, Integer> statisticSalaryRange() {
        List<String> ranges = userRepository.findAllSalaryRange();
        Map<String, Integer> result = new HashMap<>();
        for (String range: ranges) {
            Integer count = userRepository.countUserBySalaryRange(range);
            result.put(range, count);
        }
        return result;
    }

    @Override
    public void updateUser(UserDto user) {
        if (user.getPassword() != null) {
            if (user.getPassword().isBlank())
                throw new UserHandleException("Password không được để trống!");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new UserHandleException(""));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(user, userEntity);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserHandleException("Bạn chưa đăng nhập!"));
    }

    @Override
    public Page<User> findUserLikeName(String name, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("name").ascending());
        return userRepository.findUsersByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public User saveSurvey(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserHandleException("Người dùng không tồn tại trong hệ thống!"));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(userDto, user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserHandleException("Người dùng không tồn tại trong hệ thống!"));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(userDto, user);
        return userRepository.save(user);
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

}
