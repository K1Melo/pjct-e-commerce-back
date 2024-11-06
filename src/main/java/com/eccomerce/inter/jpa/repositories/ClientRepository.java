package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<User, Long> {

}
