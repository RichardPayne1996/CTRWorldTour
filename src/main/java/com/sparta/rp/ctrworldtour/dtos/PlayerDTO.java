package com.sparta.rp.ctrworldtour.dtos;

public class PlayerDTO {
    private int  id;
    private String username;
    private String name;
    private Integer handicap;
    private Integer score;

    public PlayerDTO(int id, String username, String name, Integer handicap, Integer score) {
        this.username = username;
        this.name = name;
        this.handicap = handicap;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
