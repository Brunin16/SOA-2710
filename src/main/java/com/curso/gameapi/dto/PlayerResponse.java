package com.curso.gameapi.dto;

import com.curso.gameapi.models.Game;

public record PlayerResponse (
        Integer id,
        String nome,
        Game gameFav
){ }
