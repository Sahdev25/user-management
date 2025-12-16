package com.wisemonk.user_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RoleDto {
    @NotNull(message = "role cannot be null")
    private String role;
}
