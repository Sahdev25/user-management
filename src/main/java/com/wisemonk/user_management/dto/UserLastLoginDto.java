package com.wisemonk.user_management.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLastLoginDto {

    private UUID userId;
    private String email;
    private LocalDateTime lastLogin;
}