package com.sparta.rp.ctrworldtour.controllers;

import com.sparta.rp.ctrworldtour.dtos.PlayerDTO;
import com.sparta.rp.ctrworldtour.dtos.PlayerMapper;
import com.sparta.rp.ctrworldtour.entities.Player;
import com.sparta.rp.ctrworldtour.repositories.PlayerRepository;
import com.sparta.rp.ctrworldtour.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper, PlayerRepository playerRepository) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
        this.playerRepository = playerRepository;
    }

    @GetMapping(value="/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        var players = playerService.getAllPlayers();
        return ResponseEntity.ok().body(players);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable int id){
        var player = playerService.getPlayerById(id);
        if (player == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(player);
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO){
        Player player = playerMapper.toEntity(playerDTO);
        Player saved = playerRepository.save(player);
        return ResponseEntity.ok().body(playerMapper.toDTO(saved));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable int id, @RequestBody PlayerDTO player){
        PlayerDTO oldPlayer = playerService.getPlayerById(id);
        if (oldPlayer == null){
            return ResponseEntity.notFound().build();
        }
        if (player.getName() == null || player.getName().isEmpty()){
            player.setName(oldPlayer.getName());
        }
        if (player.getHandicap() == null) {
            player.setHandicap(oldPlayer.getHandicap());
        }
        if (player.getScore() == null) {
            player.setScore(oldPlayer.getScore());
        }
        if (player.getUsername() == null || player.getUsername().isEmpty()){
            player.setUsername(oldPlayer.getUsername());
        }

        PlayerDTO updatedPlayer = playerService.updatePlayerById(id, player);
        return ResponseEntity.ok().body(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayerDTO> deletePlayerById(@PathVariable int id){
        boolean isDeleted =  playerService.deletePlayerById(id);
        if (isDeleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
