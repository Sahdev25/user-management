package com.wisemonk.user_management.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleRequest {

    @Valid
    @NotNull(message = "rolesList cannot be null")
    private List<RoleDto> roles;

}
