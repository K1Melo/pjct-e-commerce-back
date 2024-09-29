package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
