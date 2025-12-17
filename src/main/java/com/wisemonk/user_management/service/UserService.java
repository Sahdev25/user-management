package com.wisemonk.user_management.service;

import com.wisemonk.user_management.dto.AssignRoleRequest;
import com.wisemonk.user_management.dto.LoginRequest;
import com.wisemonk.user_management.dto.LoginResponse;
import com.wisemonk.user_management.dto.RegistrationRequest;
import com.wisemonk.user_management.dto.RegistrationResponse;
import com.wisemonk.user_management.dto.UserProfileResponse;
import com.wisemonk.user_management.model.Role;
import com.wisemonk.user_management.model.Users;
import com.wisemonk.user_management.repository.RolesRepository;
import com.wisemonk.user_management.repository.UserRepository;
import com.wisemonk.user_management.security.JwtUtil;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    
    public UserProfileResponse getCurrentUserProfile(Authentication authentication) {

        String email = authentication.getName();

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }
    
    @Transactional
    public void assignRoles(UUID userId, AssignRoleRequest request) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<Role> roles = request.getRoles()
                .stream()
                .map(roleDto ->
                        rolesRepository.findByName(roleDto.getRole())
                                .orElseThrow(() ->
                                        new EntityNotFoundException(
                                                "Role not found: " + roleDto.getRole()
                                        )
                                )
                )
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
    }


}
