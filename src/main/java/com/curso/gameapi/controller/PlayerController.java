package com.curso.gameapi.controller;

import com.curso.gameapi.dto.PlayerMapper;
import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.dto.PlayerResponse;
import com.curso.gameapi.models.Player;
import com.curso.gameapi.repository.PlayerRepository;
import com.curso.gameapi.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
@Tag(name= "Players", description = "CRUD de Players")
public class PlayerController {

    private final PlayerService pr;

    // GET (lista todos)
    @Operation(summary = "Lista todos os players")
    @GetMapping
    public List<PlayerResponse> list(){
        return pr.getAll();
    }

    // GET (busca por id)
    @Operation(summary = "Busca um player por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(pr.getById(id));
    }

    // CREATE
    @Operation(summary = "Cria um novo player")
    @PostMapping
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest request){
        return ResponseEntity.ok(pr.create(request));
    }

    // UPDATE
    @Operation(summary = "Atualiza um player existente")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody PlayerRequest request){
        return ResponseEntity.ok(pr.update(request, id));
    }

    // DELETE
    @Operation(summary = "Deleta um player por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return ResponseEntity.ok(pr.delete(id));
    }
}




















