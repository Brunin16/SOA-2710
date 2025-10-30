package com.curso.gameapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    //Propriedades
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlayer;

    @Column
    private String nome;

    //Indicando Relacionamento
    @ManyToOne
    //Indicando o nome da FK
    @JoinColumn(name = "fav_game")
    private Game gameFav;
}