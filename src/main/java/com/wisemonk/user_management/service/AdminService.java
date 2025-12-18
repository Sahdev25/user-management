package com.wisemonk.user_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wisemonk.user_management.dto.AdminStatsResponse;
import com.wisemonk.user_management.dto.UserLastLoginDto;
import com.wisemonk.user_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public AdminStatsResponse getAdminStats() {

        long totalUsers = userRepository.count();

        List<UserLastLoginDto> lastLoginDetails = userRepository
                .findAll()
                .stream()
                .map(user -> UserLastLoginDto.builder()
                        .userId(user.getId())
                        .email(user.getEmail())
                        .lastLogin(user.getLastLogin())
                        .build()
                )
                .toList();

        return AdminStatsResponse.builder()
                .totalUsers(totalUsers)
                .lastLoginDetails(lastLoginDetails)
                .build();
    }
}
