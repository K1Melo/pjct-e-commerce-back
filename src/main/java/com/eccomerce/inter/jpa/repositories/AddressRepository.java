package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
