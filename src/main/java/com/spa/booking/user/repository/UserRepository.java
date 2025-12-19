package com.spa.booking.user.repository;

import com.spa.booking.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserProfile, UUID> {
}
