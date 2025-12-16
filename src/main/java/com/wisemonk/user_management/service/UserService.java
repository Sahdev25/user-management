package com.wisemonk.user_management.service;

import com.wisemonk.user_management.dto.LoginRequest;
import com.wisemonk.user_management.dto.LoginResponse;
import com.wisemonk.user_management.dto.RegistrationRequest;
import com.wisemonk.user_management.dto.RegistrationResponse;
import com.wisemonk.user_management.model.Role;
import com.wisemonk.user_management.model.Users;
import com.wisemonk.user_management.repository.RolesRepository;
import com.wisemonk.user_management.repository.UserRepository;
import com.wisemonk.user_management.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        Optional<Users> users = userRepository.findByEmail(loginRequest.getEmail());


        users.ifPresent(user -> {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
        });



        return LoginResponse.builder()
                .token(token)
                .email(users.map(Users::getEmail).orElse(null))
                .username(users.map(Users::getUsername).orElse(null))
                .build();
    }

    public RegistrationResponse registerUser(RegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Role userRole = rolesRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role USER not found"));

        Users user = Users.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);

        return RegistrationResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .active(user.getEnabled())
                .message("User registered successfully")
                .build();
    }


}
