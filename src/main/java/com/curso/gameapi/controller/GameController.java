package com.curso.gameapi.controller;

import com.curso.gameapi.dto.GameMapper;
import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import com.curso.gameapi.models.Game;
import com.curso.gameapi.repository.GameRepository;
import com.curso.gameapi.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/games")
@Tag(name = "Games", description = "CRUD de Games")
public class GameController {

    @Autowired
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    // GET (lista todos)
    @Operation(summary = "Lista todos os games")
    @GetMapping
    public List<GameResponse> list() {
        return service.getAll();
    }

    // GET (busca por id)
    @Operation(summary = "Busca um game por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> findById(@PathVariable Integer id) {
        return  ResponseEntity.ok(service.getById(id));
    }

    // CREATE
    @Operation(summary = "Cria um novo game")
    @PostMapping
    public ResponseEntity<GameResponse> create(@Valid @RequestBody GameRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    // UPDATE
    @Operation(summary = "Atualiza um game existente")
    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody GameRequest request) {
        return ResponseEntity.ok(service.update(request, id));
    }

    // DELETE
    @Operation(summary = "Deleta um game por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}