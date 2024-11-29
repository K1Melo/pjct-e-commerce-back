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

@Service
public class GameImageService {

    @Autowired
    private GameImageRepository gameImageRepository;

    @Autowired
    private GameRepository gameRepository;

    private static final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/image";
    private static final String IMGUR_CLIENT_ID = "SEU_CLIENT_ID"; // Substitua pelo Client ID da sua conta Imgur

    public List<GameImage> getAll() {
        return gameImageRepository.findAll();
    }

    public List<GameImage> getByGame(Long gameId) {
        return gameImageRepository.findByGameId(gameId);
    }

    public GameImage add(Long gameId, MultipartFile file) throws IOException {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado"));
        GameImage gameImage = new GameImage();

        // Fazer upload da imagem para o Imgur
        String imageUrl = uploadToImgur(file);

        // Configurar os atributos da entidade
        gameImage.setName(file.getOriginalFilename());
        gameImage.setUrl(imageUrl);
        gameImage.setGame(game);
        gameImage.setUpdateDate(new Date());
        gameImage.setCreationDate(new Date());

        // Salvar no banco de dados
        return gameImageRepository.saveAndFlush(gameImage);
    }

    private String uploadToImgur(MultipartFile file) throws IOException {
        // Configurar o cliente HTTP
        OkHttpClient client = new OkHttpClient();

        // Converter o arquivo em bytes
        RequestBody fileBody = RequestBody.create(file.getBytes(), MediaType.parse(file.getContentType()));

        // Criar o corpo da requisição
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getOriginalFilename(), fileBody)
                .build();

        // Configurar a requisição HTTP
        Request request = new Request.Builder()
                .url(IMGUR_UPLOAD_URL)
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .post(requestBody)
                .build();

        // Enviar a requisição
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Falha ao fazer upload para o Imgur: " + response.message());
        }

        // Extrair a URL da imagem da resposta
        String responseBody = response.body().string();
        String imageUrl = parseImageUrl(responseBody);

        response.close();
        return imageUrl;
    }

    private String parseImageUrl(String responseBody) {
        // Extração simples do campo "link" da resposta JSON
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
