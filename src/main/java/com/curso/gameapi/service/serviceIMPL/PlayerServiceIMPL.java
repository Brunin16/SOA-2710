package com.curso.gameapi.service.serviceIMPL;

import com.curso.gameapi.dto.PlayerMapper;
import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.dto.PlayerResponse;
import com.curso.gameapi.exceptions.NotFoundException;
import com.curso.gameapi.models.Player;
import com.curso.gameapi.repository.GameRepository;
import com.curso.gameapi.repository.PlayerRepository;
import com.curso.gameapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PlayerServiceIMPL implements PlayerService {

    private final PlayerRepository repository;
    private final GameRepository gr;


    @Override
    public List<PlayerResponse> getAll() {
        return repository.findAll().stream()
                .map(PlayerMapper::toResponse)
                .collect(toList());
    }

    @Override
    public PlayerResponse getById(Integer id) {
        return repository.findById(id)
                .map(player -> ResponseEntity.ok(PlayerMapper.toResponse(player)))
                .orElseThrow(() -> new NotFoundException("Player não encontrado.")).getBody();
    }

    @Override
    public PlayerResponse update(PlayerRequest g, Integer id) {
        return repository.findById(id)
                .map(player -> {
                    player.setNome(g.nome());
                    player.setGameFav(g.gameFav());
                    Player update = repository.save(player);
                    return ResponseEntity.ok(PlayerMapper.toResponse(update));
                })
                .orElseThrow(() -> new NotFoundException("Game não encontrado.")).getBody();
    }

    @Override
    public Void delete(Integer id) {
        repository.findById(id)
                .ifPresentOrElse(
                        player -> repository.delete(player),
                        () -> {
                            throw new NotFoundException("Game não encontrado.");
                        }
                );
        return null;
    }

    @Override
    public PlayerResponse create(PlayerRequest req) {
        Player player = PlayerMapper.toEntity(req);

        player.setGameFav(gr.findById(req.gameFav().getIdGame())
                    .orElseThrow(() -> new NotFoundException("Game não encontrado com id: " + req.gameFav().getIdGame())));

        Player saved = repository.save(player);
        return PlayerMapper.toResponse(saved);
    }

}
