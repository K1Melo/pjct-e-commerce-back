package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
