package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
