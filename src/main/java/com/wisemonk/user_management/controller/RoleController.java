package com.wisemonk.user_management.controller;

import com.wisemonk.user_management.constants.ApiConstants;
import com.wisemonk.user_management.dto.RolesCreatedResponse;
import com.wisemonk.user_management.dto.RolesRequest;
import com.wisemonk.user_management.service.RoleService;
import com.wisemonk.user_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.ROLES)
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RolesCreatedResponse> createRoles(
            @Valid @RequestBody RolesRequest request
            ) {
    	return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.createRoles(request));
    }

}
