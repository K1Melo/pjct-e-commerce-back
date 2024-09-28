package com.eccomerce.inter.resources.jdbc.repository;

import com.eccomerce.inter.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJdbc extends JpaRepository<User, UUID> {
}
