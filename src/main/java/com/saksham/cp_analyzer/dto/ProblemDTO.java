package com.saksham.cp_analyzer.dto;

public class ProblemDTO {

    private String title;
    private String difficulty;
    private String platform;
    private String topic;
    private String problemUrl;

    // getters

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTopic() {
        return topic;
    }

    public String getProblemUrl() {
        return problemUrl;
    }

    // setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setProblemUrl(String problemUrl) {
        this.problemUrl = problemUrl;
    }
}
