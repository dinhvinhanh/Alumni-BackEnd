package com.thesis.alumni.system.initial;

import com.thesis.alumni.system.entity.Role;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.repository.RoleRepository;
import com.thesis.alumni.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public void run(ApplicationArguments args) {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        if (roleRepository.findRoleByName("ROLE_ADMIN") == null) {
            roleAdmin = roleRepository.save(roleAdmin);
        }
        if (roleRepository.findRoleByName("ROLE_USER") == null) {
            roleUser = roleRepository.save(roleUser);
        }

        List<Role> roles = List.of(roleAdmin, roleUser);
        if (userRepository.findUserByEmail("nguyenhuuvuno1@gmail.com") == null)
            userRepository.save(new User("Nguyen VU", "nguyenhuuvuno1@gmail.com", "1100", true, roles));
    }
}