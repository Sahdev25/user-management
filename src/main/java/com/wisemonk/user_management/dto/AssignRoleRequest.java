package com.wisemonk.user_management.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AssignRoleRequest {

    @NotNull
    private String username;

    @Valid
    @NotNull(message = "rolesList cannot be null")
    private List<RoleDto> roles;

}
