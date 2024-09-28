package com.eccomerce.inter.jdbc.repositories;

import com.eccomerce.inter.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
