package com.curso.gameapi.service;

import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.dto.PlayerResponse;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getAll();
    PlayerResponse getById(Integer id);
    PlayerResponse update(PlayerRequest g, Integer id);
    Void delete(Integer id);
    PlayerResponse create(PlayerRequest g);
}
