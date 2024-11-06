package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.PermissionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionUserRepository extends JpaRepository<PermissionUser, Long> {
}
