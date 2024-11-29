package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Game;
import com.eccomerce.inter.domain.entities.GameImage;
import com.eccomerce.inter.jpa.repositories.GameImageRepository;
import com.eccomerce.inter.jpa.repositories.GameRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class GameImageService {

    @Autowired
    private GameImageRepository gameImageRepository;

    @Autowired
    private GameRepository gameRepository;

    private static final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/image";
    private static final String IMGUR_CLIENT_ID = "129f522ad94fc32";

    // Reutilizando o OkHttpClient com timeouts configurados
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    public List<GameImage> getAll() {
        return gameImageRepository.findAll();
    }

    public List<GameImage> getByGame(Long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        return gameImageRepository.findByGame(game);
    }

    public GameImage add(Long gameId, MultipartFile file) throws IOException {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado"));
        GameImage gameImage = new GameImage();

        String imageUrl = uploadToImgur(file);

        gameImage.setName(file.getOriginalFilename());
        gameImage.setUrl(imageUrl);
        gameImage.setGame(game);
        gameImage.setUpdateDate(new Date());
        gameImage.setCreationDate(new Date());

        return gameImageRepository.saveAndFlush(gameImage);
    }

    private String uploadToImgur(MultipartFile file) throws IOException {

        RequestBody fileBody = RequestBody.create(file.getBytes(), MediaType.parse(file.getContentType()));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getOriginalFilename(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url(IMGUR_UPLOAD_URL)
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Falha ao fazer upload para o Imgur: " + response.message());
//            }
            String responseBody = response.body().string();
            return parseImageUrl(responseBody);
        }
    }

    private String parseImageUrl(String responseBody) {
        int startIndex = responseBody.indexOf("\"link\":\"") + 8;
        int endIndex = responseBody.indexOf("\"", startIndex);
        return responseBody.substring(startIndex, endIndex).replace("\\/", "/");
    }

    public GameImage change(GameImage gameImage) {
        gameImage.setUpdateDate(new Date());
        return gameImageRepository.saveAndFlush(gameImage);
    }

    public void del(Long id) {
        GameImage gameImage = gameImageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Imagem não encontrada"));
        gameImageRepository.delete(gameImage);
    }

    public void del() {
        gameImageRepository.deleteAll();
    }
}
