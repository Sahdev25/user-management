package com.wisemonk.user_management.controller;

import com.wisemonk.user_management.constants.ApiConstants;
import com.wisemonk.user_management.dto.AdminStatsResponse;
import com.wisemonk.user_management.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.ADMIN)
public class AdminController {
	
	private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ApiConstants.STATS)
    public ResponseEntity<AdminStatsResponse> getStats() {
        return ResponseEntity.ok(adminService.getAdminStats());
    }
}
