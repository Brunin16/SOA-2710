package com.curso.gameapi.api.config;

import com.curso.gameapi.dto.GameMapper;
import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.models.Game;
import com.curso.gameapi.models.Player;
import com.curso.gameapi.service.GameService;
import com.curso.gameapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Year;

@Configuration
@RequiredArgsConstructor
@Profile({"dev"})
public class DatabaseSeeder implements CommandLineRunner {

    final GameService gs;
    final PlayerService ps;


    @Override
    public void run(String... args) throws Exception {
        GameRequest game = new GameRequest("Contra","Sega","acao", 1990);
        GameResponse gr = gs.create(game);

        PlayerRequest player = new PlayerRequest("Bruno", new Game(gr.id(),gr.titulo(),gr.editora(),gr.genero(),Year.of(gr.anoLancamento())));
        ps.create(player);


    }
}
