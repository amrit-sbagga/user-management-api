package com.amrit.user_management_api.specification;

import org.springframework.data.jpa.domain.Specification;
import com.amrit.user_management_api.entity.User;

public class UserSpecification {

    public static Specification<User> hasMinAge(Integer minAge) {
        return (root, query, cb) ->
                minAge == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("age"), minAge);
    }

    public static Specification<User> hasRole(String role) {
        return (root, query, cb) ->
                role == null ? cb.conjunction() : cb.equal(root.get("role"), role);
    }

    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) ->
                username == null ? cb.conjunction() : cb.like(root.get("username"), "%" + username + "%");
    }
}
