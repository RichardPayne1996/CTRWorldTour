package com.sparta.rp.ctrworldtour.repositories;

import com.sparta.rp.ctrworldtour.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    public List<Player> findAllByOrderByScoreAsc();
}
