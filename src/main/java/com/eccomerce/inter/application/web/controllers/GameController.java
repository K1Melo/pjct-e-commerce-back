package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Game;
import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.domain.services.GameService;
import com.eccomerce.inter.domain.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/")
    public List<Game> getAll() {
        return gameService.getAll();
    }


    @PostMapping("/")
    public Game add(@RequestBody Game game) {
        return gameService.add(game);
    }

    @PutMapping("/")
    public Game change(@RequestBody Game game) {
        return gameService.change(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        gameService.del(id);
        return ResponseEntity.ok().build();
    }
}
