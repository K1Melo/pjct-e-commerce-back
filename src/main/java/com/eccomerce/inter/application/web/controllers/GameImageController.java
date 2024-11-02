package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.GameImage;
import com.eccomerce.inter.domain.services.GameImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/gameimage")
public class GameImageController {
    @Autowired
    GameImageService gameImageService;

    @GetMapping("/")
    public List<GameImage> getAll() {
        return gameImageService.getAll();
    }

    @PostMapping("/")
    public GameImage add(@RequestParam("game") Long gameId, @RequestParam MultipartFile file) throws IOException {
        return gameImageService.add(gameId, file);
    }

    @PutMapping("/")
    public GameImage change(@RequestBody GameImage gameImage) {
        return gameImageService.change(gameImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        gameImageService.del(id);
        return ResponseEntity.ok().build();
    }
}