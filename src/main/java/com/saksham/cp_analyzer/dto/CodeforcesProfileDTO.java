package com.saksham.cp_analyzer.dto;

public class CodeforcesProfileDTO {

    private String handle;

    private Integer currentRating;

    private Integer maxRating;

    private Integer contestsParticipated;

    private Integer bestRank;

    private Integer ratingGrowth;

    public CodeforcesProfileDTO() {
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Integer currentRating) {
        this.currentRating = currentRating;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public Integer getContestsParticipated() {
        return contestsParticipated;
    }

    public void setContestsParticipated(Integer contestsParticipated) {
        this.contestsParticipated = contestsParticipated;
    }

    public Integer getBestRank() {
        return bestRank;
    }

    public void setBestRank(Integer bestRank) {
        this.bestRank = bestRank;
    }

    public Integer getRatingGrowth() {
        return ratingGrowth;
    }

    public void setRatingGrowth(Integer ratingGrowth) {
        this.ratingGrowth = ratingGrowth;
    }
}