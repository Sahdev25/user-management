package com.wisemonk.user_management.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RolesRequest {
    @Valid
    @NotNull(message = "rolesList cannot be null")
    private List<RoleDto> roles;
}
