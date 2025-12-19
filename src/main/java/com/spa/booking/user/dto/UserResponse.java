package com.spa.booking.user.dto;

import com.spa.booking.user.entity.Role;
import com.spa.booking.user.entity.UserProfile;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID userId;
    private String fullName;
    private String email;
    private String phone;
    private Role role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static UserResponse fromEntity(UserProfile userProfile) {
        UserResponse userResponse = new UserResponse();
        userResponse.userId = userProfile.getUserId();
        userResponse.fullName = userProfile.getFullName();
        userResponse.email = userProfile.getEmail();
        userResponse.phone = userProfile.getPhone();
        userResponse.role = userProfile.getRole();
        userResponse.createdAt = userProfile.getCreatedAt();
        userResponse.updatedAt = userProfile.getUpdatedAt();
        return userResponse;
    }
}
