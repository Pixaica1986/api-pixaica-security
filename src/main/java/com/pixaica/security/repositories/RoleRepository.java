package com.pixaica.security.repositories;

import com.pixaica.security.entities.Role;
import com.pixaica.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(RoleList name);
}
