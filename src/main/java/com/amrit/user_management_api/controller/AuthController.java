package com.amrit.user_management_api.controller;

import com.amrit.user_management_api.dto.ApiResponse;
import com.amrit.user_management_api.dto.request.LoginRequest;
import com.amrit.user_management_api.entity.User;
import com.amrit.user_management_api.security.JwtUtil;
import com.amrit.user_management_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody LoginRequest request) {

        // Dummy validation (we will improve later)
      //  if ("admin".equals(request.getUsername()) &&
      //          "password".equals(request.getPassword())) {
//        if (authService.validateUser(request)) {
//
//            String token = jwtUtil.generateToken(request.getUsername(), request.getRole());
//
//            return new ApiResponse<>("Login successful", token);
//        }
//
//        throw new RuntimeException("Invalid credentials");

        User user = authService.authenticate(request);

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new ApiResponse<>("Login successful", token);
    }
}
