package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Game;
import com.eccomerce.inter.jpa.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    private Date creation;

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game add(Game game) {
        game.setUpdateDate(new Date());
        game.setCreationDate(new Date());
        creation = game.getCreationDate();
        return gameRepository.saveAndFlush(game);
    }

    public Game change(Game game) {
        game.setUpdateDate(new Date());
        game.setCreationDate(creation);
        return gameRepository.saveAndFlush(game);
    }

    public void del(Long id) {
        Game game = gameRepository.findById(id).get();
        gameRepository.delete(game);
    }
}
