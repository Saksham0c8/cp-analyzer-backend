package com.saksham.cp_analyzer.dto;

public class ProblemRecommendationDTO {

    private String title;
    private String difficulty;
    private String topic;
    private String platform;

    public ProblemRecommendationDTO() {
    }

    public ProblemRecommendationDTO(
            String title,
            String difficulty,
            String topic,
            String platform
    ) {
        this.title = title;
        this.difficulty = difficulty;
        this.topic = topic;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
