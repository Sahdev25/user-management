package com.wisemonk.user_management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LoginResponse {

    private String email;
    private String username;
    private String token;

}
