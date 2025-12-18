package com.wisemonk.user_management.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatsResponse {

    private long totalUsers;
    private List<UserLastLoginDto> lastLoginDetails;
}
