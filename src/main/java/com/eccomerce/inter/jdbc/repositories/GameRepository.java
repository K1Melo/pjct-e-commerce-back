package com.eccomerce.inter.jdbc.repositories;

import com.eccomerce.inter.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
