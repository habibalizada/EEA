package com.estockmarket.estockmarketuser.repository;

import com.estockmarket.estockmarketuser.models.ERole;
import com.estockmarket.estockmarketuser.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

