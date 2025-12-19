package com.spa.booking.user.controller;

import com.spa.booking.user.dto.UserResponse;
import com.spa.booking.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/me")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> me(
            @RequestHeader(value = "X-User-Id", required = false) UUID userId,
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Roles", required = false) String roles
    ) {
        UserResponse response = userService.getOrCreateUser(userId, email, roles);
        return ResponseEntity.ok(response);
    }

}
