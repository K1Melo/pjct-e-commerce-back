package com.eccomerce.inter.jpa.repositories;

import com.eccomerce.inter.domain.entities.Game;
import com.eccomerce.inter.domain.entities.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
}
