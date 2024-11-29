package com.eccomerce.inter.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tb_game_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    @JoinColumn(name="game")
    private Game game;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private String url;
    @Transient
    private byte[] file;

    public GameImage(Long id) {
        this.id = id;
    }
}
