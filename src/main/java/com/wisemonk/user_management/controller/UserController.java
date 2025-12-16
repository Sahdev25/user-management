package com.wisemonk.user_management.controller;

import com.wisemonk.user_management.constants.ApiConstants;
import com.wisemonk.user_management.dto.*;
import com.wisemonk.user_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.USERS)
public class UserController {

    private final UserService userService;

//    @GetMapping(ApiConstants.ME)
//    public ResponseEntity<?> getUser(@RequestParam String username) {
//
//    }

    @PostMapping(ApiConstants.REGISTER)
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody RegistrationRequest request
            ) {

        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping(ApiConstants.LOGIN)
    public ResponseEntity<LoginResponse> loginUser(
        @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(userService.loginUser(request));
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping(ApiConstants.USERID + ApiConstants.ROLES)
//    public ResponseEntity<?> assignRole(
//            @Valid @RequestBody AssignRoleRequest request
//    ){
//
//    }

}
