package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Game;
import com.eccomerce.inter.domain.entities.GameImage;
import com.eccomerce.inter.jpa.repositories.GameImageRepository;
import com.eccomerce.inter.jpa.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class GameImageService {
    @Autowired
    private GameImageRepository gameImageRepository;

    @Autowired
    private GameRepository gameRepository;
    private Date creation;

    public List<GameImage> getAll() {
        return gameImageRepository.findAll();
    }

    public GameImage add(Long productId, MultipartFile file) throws IOException {
        Game game = gameRepository.findById(productId).get();
        GameImage gameImage = new GameImage();

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String imageName = String.valueOf(game.getId()) + file.getOriginalFilename();
                Path path = Paths.get("C:/Users/k1fer/dev/game/front/assets/" + imageName);

                gameImage.setName(imageName);

                Files.write(path, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameImage.setGame(game);

        gameImage.setUpdateDate(new Date());
        gameImage.setCreationDate(new Date());
        creation = gameImage.getCreationDate();

        return gameImageRepository.saveAndFlush(gameImage);
    }

    public GameImage change(GameImage gameImage) {
        gameImage.setUpdateDate(new Date());
        gameImage.setCreationDate(creation);
        return gameImageRepository.saveAndFlush(gameImage);
    }

    public void del(Long id) {
        GameImage gameImage = gameImageRepository.findById(id).get();
        gameImageRepository.delete(gameImage);
    }
}
