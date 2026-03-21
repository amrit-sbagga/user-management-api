package com.amrit.user_management_api.controller;

import com.amrit.user_management_api.dto.request.CreateUserRequest;
import com.amrit.user_management_api.dto.request.PagedResponse;
import com.amrit.user_management_api.dto.request.UpdateUserRequest;
import com.amrit.user_management_api.dto.ApiResponse;
import com.amrit.user_management_api.dto.response.UserResponse;
import com.amrit.user_management_api.entity.User;
import com.amrit.user_management_api.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
       // User user = new User(request.getName(), request.getAge());

        User userObj = new User(request.getUsername(), request.getPassword(), request.getAge(), request.getRole());
        // userObj.setRole(Role.USER); // default role

        UserResponse response = userService.createUser(userObj);
        log.info("User created: {}", response);

//        UserResponse response = new UserResponse(
//                user.getId(),
//                user.getUsername(),
//                user.getAge()
//        );

        return new ApiResponse<>("User created successfully", response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/userlist")
    public ApiResponse<List<UserResponse>> getUsers() {
        return new ApiResponse<>("Users fetched successfully", userService.getUsers());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable int id) {
        return new ApiResponse<>("User fetched successfully", userService.getUser(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable int id,
            @RequestBody UpdateUserRequest request
    ) {
        User user = new User(request.getUsername(), request.getAge());
        return new ApiResponse<>(
                "User updated successfully",
                userService.updateUser(id, user)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ApiResponse<>("User deleted successfully", null);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/username/{username}")
    public ApiResponse<Optional<UserResponse>> getByUsername(@PathVariable String username) {
        return new ApiResponse<>("Users fetched successfully", userService.getUsersByUsername(username));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/above/{age}")
    public ApiResponse<List<UserResponse>> findUsersOlderThan(@PathVariable int age) {
        return new ApiResponse<>("Users fetched successfully", userService.findUsersOlderThan(age));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/aboveNative/{age}")
    public ApiResponse<List<UserResponse>> findUsersOlderThanNative(@PathVariable int age) {
        return new ApiResponse<>("Users fetched successfully",
                userService.findUsersOlderThanNative(age));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/page")
    public ApiResponse<PagedResponse<UserResponse>> getUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return new ApiResponse<>("Users fetched successfully",
                userService.getUsersPaginated(page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/sortByAge")
    public ApiResponse<List<UserResponse>> getUsersSortedByAge() {
        return new ApiResponse<>("Users fetched successfully", userService.getUsersSorted());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/pageWithSort")
    public ApiResponse<PagedResponse<UserResponse>> getUsersPaginatedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ApiResponse<>("Users fetched successfully",
                userService.getUsersPaginatedAndSorted(page, size, sortBy));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/pageWithSortAndFilter")
    public ApiResponse<PagedResponse<UserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) String role
    ) {
        return new ApiResponse<>(
                "Users fetched successfully",
                userService.getUsersPaginatedPlusSortedAndFiltered(page, size, sortBy, direction, minAge, role)
        );
    }

}
