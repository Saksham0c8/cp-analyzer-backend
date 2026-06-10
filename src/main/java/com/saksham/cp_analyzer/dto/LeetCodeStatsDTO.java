package com.saksham.cp_analyzer.dto;

public class LeetCodeStatsDTO {

    private String username;
    private int totalSolved;
    private int easySolved;
    private int mediumSolved;
    private int hardSolved;
    private int ranking;

    public LeetCodeStatsDTO() {
    }

    public LeetCodeStatsDTO(
            String username,
            int totalSolved,
            int easySolved,
            int mediumSolved,
            int hardSolved,
            int ranking
    ) {
        this.username = username;
        this.totalSolved = totalSolved;
        this.easySolved = easySolved;
        this.mediumSolved = mediumSolved;
        this.hardSolved = hardSolved;
        this.ranking = ranking;
    }

    public String getUsername() {
        return username;
    }

    public int getTotalSolved() {
        return totalSolved;
    }

    public int getEasySolved() {
        return easySolved;
    }

    public int getMediumSolved() {
        return mediumSolved;
    }

    public int getHardSolved() {
        return hardSolved;
    }

    public int getRanking() {
        return ranking;
    }
}