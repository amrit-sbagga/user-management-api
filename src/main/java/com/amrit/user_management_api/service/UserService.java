package com.amrit.user_management_api.service;

import com.amrit.user_management_api.dto.request.PagedResponse;
import com.amrit.user_management_api.dto.response.UserResponse;
import com.amrit.user_management_api.entity.User;
import com.amrit.user_management_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setAge(user.getAge());
        res.setRole(user.getRole());
        return res;
    }

    public UserResponse createUser(User user) {
        user.setUsername(user.getUsername().toLowerCase());
        // 🔐 hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponse res = new UserResponse();
                    res.setId(user.getId());
                    res.setUsername(user.getUsername());
                    res.setAge(user.getAge());
                    res.setRole(user.getRole());
                    return res;
                })
                .toList();
    }

    public UserResponse getUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    public UserResponse updateUser(int id, User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updatedUser.getUsername() != null) {
            existing.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getAge() != null) {
            existing.setAge(updatedUser.getAge());
        }

        User saved = userRepository.save(existing);
        return mapToResponse(saved);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Optional<UserResponse> getUsersByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(this::mapToResponse);
    }

    public List<UserResponse> findUsersOlderThan(int age) {
        return userRepository.findUsersOlderThan(age)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<UserResponse> findUsersOlderThanNative(int age) {

        return userRepository.findUsersOlderThanNative(age)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<UserResponse> getUsersSorted() {
        return userRepository.findAll(Sort.by("age").descending())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PagedResponse<UserResponse> getUsersPaginated(int page, int size) {
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size));

        List<UserResponse> users = usersPage.getContent().stream()
                .map(this::mapToResponse).toList();

        PagedResponse<UserResponse> response = new PagedResponse<>();
        response.setContent(users);
        response.setPage(usersPage.getNumber());
        response.setSize(usersPage.getSize());
        response.setTotalElements(usersPage.getTotalElements());
        response.setTotalPages(usersPage.getTotalPages());

        return response;
    }

    public PagedResponse<UserResponse> getUsersPaginatedAndSorted(int page, int size, String sortBy) {
        Page<User> usersPage = userRepository.findAll(
                PageRequest.of(page, size, Sort.by(sortBy).descending())
        );
        List<UserResponse> users = usersPage.getContent().stream()
                .map(this::mapToResponse).toList();

        PagedResponse<UserResponse> response = new PagedResponse<>();
        response.setContent(users);
        response.setPage(usersPage.getNumber());
        response.setSize(usersPage.getSize());
        response.setTotalElements(usersPage.getTotalElements());
        response.setTotalPages(usersPage.getTotalPages());

        return response;
    }
}
