package com.curso.gameapi.service.serviceIMPL;

import com.curso.gameapi.dto.GameMapper;
import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import com.curso.gameapi.exceptions.NotFoundException;
import com.curso.gameapi.models.Game;
import com.curso.gameapi.repository.GameRepository;
import com.curso.gameapi.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceIMPL implements GameService {

    private final GameRepository gr;

    @Override
    public List<GameResponse> getAll() {
        return gr.findAll().stream().map(GameMapper::toResponse).toList();
    }

    @Override
    public GameResponse getById(Integer id) {
        return gr.findById(id)
                .map(game -> ResponseEntity.ok(GameMapper.toResponse(game)))
                .orElseThrow(() -> new NotFoundException("Game não encontrado.")).getBody();
    }

    @Override
    public GameResponse update(GameRequest g, Integer id) {
        return gr.findById(id)
                .map(game -> {
                    game.setTitulo(g.titulo());
                    game.setEditora(g.editora());
                    game.setGenero(g.genero());
                    game.setAnoLancamento(Year.of(g.anoLancamento()));

                    Game updated = gr.save(game);
                    return ResponseEntity.ok(GameMapper.toResponse(updated));
                })
                .orElseThrow(() -> new NotFoundException("Game não encontrado.")).getBody();
    }

    @Override
    public Void delete(Integer id) {
        gr.findById(id)
                .ifPresentOrElse(
                        game -> gr.delete(game),
                        () -> {throw  new NotFoundException("Game não encontrado."); }
                );
        return null;
    }


    @Override
    public GameResponse create(GameRequest g) {
        Game saved = gr.save(GameMapper.toEntity(g));
        return GameMapper.toResponse(saved);
    }
}
