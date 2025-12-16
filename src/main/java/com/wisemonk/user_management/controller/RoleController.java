package com.wisemonk.user_management.controller;

import com.wisemonk.user_management.constants.ApiConstants;
import com.wisemonk.user_management.dto.RolesRequest;
import com.wisemonk.user_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.ROLES)
public class RoleController {

    private final UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping(ApiConstants.ROLES)
//    public ResponseEntity<?> createRoles(
//            @Valid @RequestBody RolesRequest request
//            ) {
//        return ResponseEntity.ok();
//    }

}
