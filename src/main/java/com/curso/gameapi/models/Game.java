package com.curso.gameapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

//Anotação para indicar a classe como uma entidade
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {

    //Propriedades
    //Indicando a chave primaria
    @Id
    //Indicando auto geração
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGame;

    @Column
    private String titulo;

    @Column
    private String editora;

    @Column
    private String genero;

    @Column
    private Year anoLancamento;


}
