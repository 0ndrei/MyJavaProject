package com.example.CoronaVirusInfo.repository;

import com.example.CoronaVirusInfo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

