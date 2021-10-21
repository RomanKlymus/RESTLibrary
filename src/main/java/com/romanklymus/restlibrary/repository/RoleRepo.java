package com.romanklymus.restlibrary.repository;

import com.romanklymus.restlibrary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
