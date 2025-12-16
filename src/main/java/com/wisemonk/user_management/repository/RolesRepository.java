package com.wisemonk.user_management.repository;

import com.wisemonk.user_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String user);
}
