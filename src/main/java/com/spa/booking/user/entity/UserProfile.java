package com.spa.booking.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserProfile {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
