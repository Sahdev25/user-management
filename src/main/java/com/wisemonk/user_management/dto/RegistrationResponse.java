package com.wisemonk.user_management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegistrationResponse {
    private String username;
    private String email;
    private Boolean active;
    private String message;
}
