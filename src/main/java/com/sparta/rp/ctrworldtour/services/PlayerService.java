package com.sparta.rp.ctrworldtour.services;

import com.sparta.rp.ctrworldtour.dtos.PlayerDTO;
import com.sparta.rp.ctrworldtour.dtos.PlayerMapper;
import com.sparta.rp.ctrworldtour.entities.Player;
import com.sparta.rp.ctrworldtour.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerMapper playerMapper, PlayerRepository playerRepository) {
        if (playerMapper == null || playerRepository == null) {
            throw new IllegalArgumentException();
        }
        this.playerMapper = playerMapper;
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO getPlayerById(int id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDTO).orElse(null);
    }

    public PlayerDTO savePlayer(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }

    public boolean deletePlayerById(int id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }

    public PlayerDTO updatePlayerById(int id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with id " + id + " not found"));
        player.setPlayerName(playerDTO.getPlayerName());
        player.setHandicap(playerDTO.getHandicap());
        player.setScore(playerDTO.getScore());
        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }

    public void preliminaries() {
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            player.setScore(0);
        }
        playerRepository.saveAll(players);
    }

    public void mainEvent() {
        List<Player> players = playerRepository.findAll();
        calculateHandicap();
        for (Player player : players) {
            player.setScore(player.getHandicap());
            player.setHandicap(0);
        }
        playerRepository.saveAll(players);
    }

    public List<PlayerDTO> getLeaderboard() {
        return playerRepository.findAllByOrderByScoreAsc()
                .stream()
                .map(playerMapper::toDTO)
                .toList();
    }

    public void addToScore(int playerId, int rank) {
        Player player = playerRepository.findById(playerId).orElseThrow();
        player.updateScore(rank);
        playerRepository.save(player);
    }

    public PlayerDTO getWinner() {
        List<PlayerDTO> leaderboard =  getLeaderboard();
        return leaderboard.get(0);
    }

    public void calculateHandicap() {
        int best= 1000000;
        for (Player player : playerRepository.findAll()) {
            if (player.getScore() < best) {
                best = player.getScore();
            }
        }
        for (Player player : playerRepository.findAll()) {
            player.setHandicap((player.getScore() - best) * -1);
        }
    }
}
