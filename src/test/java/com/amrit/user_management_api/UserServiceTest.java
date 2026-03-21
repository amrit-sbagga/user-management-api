package com.amrit.user_management_api;

import com.amrit.user_management_api.dto.request.Role;
import com.amrit.user_management_api.dto.response.UserResponse;
import com.amrit.user_management_api.entity.User;
import com.amrit.user_management_api.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateAndFetchUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("test123");
        user.setAge(25);
        user.setRole(Role.USER);

        UserResponse saved = userService.createUser(user);

        assertNotNull(saved.getId());
        assertEquals("testuser", saved.getUsername());
    }
}
