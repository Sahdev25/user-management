package com.wisemonk.user_management.service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wisemonk.user_management.dto.RoleDto;
import com.wisemonk.user_management.dto.RolesCreatedResponse;
import com.wisemonk.user_management.dto.RolesRequest;
import com.wisemonk.user_management.model.Role;
import com.wisemonk.user_management.repository.RolesRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

	 private final RolesRepository rolesRepository;

	    @Transactional
	    public RolesCreatedResponse createRoles(RolesRequest request) {

	        List<String> createdRoles = new ArrayList<>();

	        for (RoleDto dto : request.getRoles()) {

	            String roleName = dto.getRole().toUpperCase();

	            if (rolesRepository.findByName(roleName).isPresent()) {
	                continue; // skip duplicates
	            }

	            Role role = Role.builder()
	                    .name(roleName)
	                    .build();

	            rolesRepository.save(role);
	            createdRoles.add(roleName);
	        }
	        return RolesCreatedResponse .builder()
	                .createdRoles(createdRoles)
	                .message("Roles created successfully")
	                .build();
	    }
}
