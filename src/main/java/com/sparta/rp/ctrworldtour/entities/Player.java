package com.sparta.rp.ctrworldtour.entities;

import jakarta.persistence.*;

@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playerId")
    private int playerId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="playerName")
    private String playerName;

    @Column(name="handicap")
    private Integer handicap;

    @Column(name="score")
    private Integer score;

    public Player() {};

    public Player(String username, String password, String playerName) {
        this.username = username;
        this.password = password;
        this.playerName = playerName;
        this.handicap = 0;
        this.score = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getHandicap() {
        return handicap;
    }

    public void setHandicap(Integer handicap) {
        this.handicap = handicap;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
