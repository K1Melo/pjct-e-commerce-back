package com.eccomerce.inter.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "produto")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Double value;
    @ManyToOne
    @JoinColumn(name="mark_id")
    private Mark mark;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
