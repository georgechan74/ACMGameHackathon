package com.example.acmgamehackathon.models;

public class Game {
    private long id;
    private String winner;
    private String teamA;
    private String teamB;
    private long score;

    public Game(long id, String winner, String teamA, String teamB, long score) {
        this.id = id;
        this.winner = winner;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
