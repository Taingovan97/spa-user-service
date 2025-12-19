package com.spa.booking.user.service;

import com.spa.booking.user.entity.Role;
import com.spa.booking.user.entity.UserProfile;
import com.spa.booking.user.dto.UserResponse;
import com.spa.booking.user.repository.UserRepository;
import liquibase.license.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse getOrCreateUser(UUID userId, String email, String rolesHeader) {
        UserProfile user = userRepository.findById(userId).orElseGet(() -> {
            UserProfile u = new UserProfile();
            u.setUserId(userId);
            u.setEmail(email);
            u.setFullName("New User");
            u.setRole(retrieveRole(rolesHeader));
            u.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            u.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.save(u);
        });

        return UserResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(UUID userId) {
        UserProfile user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public UserResponse createUser(UUID userId, String email, String rolesHeader) {
        UserProfile u = new UserProfile();
        u.setUserId(userId)
                .setEmail(email)
                .setRole(retrieveRole(rolesHeader))
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        UserProfile savedUser = userRepository.save(u);
        return UserResponse.fromEntity(savedUser);
    }

    private Role retrieveRole(String rolesHeader) {
        if (rolesHeader == null) return Role.CUSTOMER;
        String r = rolesHeader.toUpperCase();
        if (r.contains("ADMIN")) return Role.ADMIN;
        if (r.contains("STAFF")) return Role.STAFF;
        return Role.CUSTOMER;
    }


}
