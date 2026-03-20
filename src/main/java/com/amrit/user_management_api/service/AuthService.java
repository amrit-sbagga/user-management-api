package com.amrit.user_management_api.service;

import com.amrit.user_management_api.dto.request.LoginRequest;
import com.amrit.user_management_api.dto.request.Role;
import com.amrit.user_management_api.entity.User;
import com.amrit.user_management_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(LoginRequest request) {
        return "admin".equals(request.getUsername()) &&
                "password".equals(request.getPassword());
    }

    public User authenticate(LoginRequest request) {

        // ADMIN user
        if ("admin".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            User user = new User();
            user.setUsername("admin");
            user.setRole(Role.ADMIN);
            return user;
        }

        // USER role
        else if ("user1".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            User user = new User();
            user.setUsername("user1");
            user.setRole(Role.USER);
            return user;
        } else {
            User user = userRepository.findByUsername(request.getUsername())
                    //.orElseThrow(() -> new RuntimeException("User not found"));
                    .orElseThrow(() ->new RuntimeException("Invalid username or password"));

            // simple password check (plain text for now)
//            if (!user.getPassword().equals(request.getPassword())) {
//                throw new RuntimeException("Invalid credentials");
//            }

            // 🔐 compare raw vs hashed
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }

            return user;
        }

       // throw new RuntimeException("Invalid credentials");
    }

}
