package com.curso.gameapi.service;

import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;

import java.util.List;

public interface GameService {
    List<GameResponse> getAll();
    GameResponse getById(Integer id);
    GameResponse update(GameRequest g, Integer id);
    Void delete(Integer id);
    GameResponse create(GameRequest g);
}
