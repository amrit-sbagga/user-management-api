package com.amrit.user_management_api.repository;

import com.amrit.user_management_api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    // List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersOlderThan(int age);

    @Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
    List<User> findUsersOlderThanNative(int age);

    // List<User> findAll(Sort sort);
    // Page<User> findAll(Pageable pageable);

    Page<User> findByAgeGreaterThanEqual(Integer age, Pageable pageable);

    Page<User> findByRole(String role, Pageable pageable);

    Page<User> findByAgeGreaterThanEqualAndRole(Integer age, String role, Pageable pageable);

}
