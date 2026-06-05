package com.saksham.cp_analyzer.dto;

public class ConsistencyDTO {

    private Long activeDays;

    private Integer longestStreak;

    private Double score;

    public ConsistencyDTO() {
    }

    public ConsistencyDTO(
            Long activeDays,
            Integer longestStreak,
            Double score
    ) {
        this.activeDays = activeDays;
        this.longestStreak = longestStreak;
        this.score = score;
    }

    public Long getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Long activeDays) {
        this.activeDays = activeDays;
    }

    public Integer getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(Integer longestStreak) {
        this.longestStreak = longestStreak;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
