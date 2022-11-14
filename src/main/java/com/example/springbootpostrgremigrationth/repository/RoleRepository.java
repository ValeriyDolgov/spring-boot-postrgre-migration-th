package com.example.springbootpostrgremigrationth.repository;

import com.example.springbootpostrgremigrationth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
