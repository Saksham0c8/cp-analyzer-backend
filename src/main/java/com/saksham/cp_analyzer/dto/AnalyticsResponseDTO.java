package com.saksham.cp_analyzer.dto;
import com.saksham.cp_analyzer.dto.ActivityStatsDTO;
import com.saksham.cp_analyzer.dto.ConsistencyDTO;

import java.util.Map;
import java.util.List;
import java.util.Map;

public class AnalyticsResponseDTO {

    private Long totalSubmissions;
    private Double consistencyScore;

    private Long acceptedSubmissions;

    private Double accuracy;

    private List<String> weakTopics;
    private Map<String, TopicStrengthDTO> topicStrength;

    private Map<String, Long> difficultyStats;

    private Map<String, Long> topicStats;
    private ActivityStatsDTO activityStats;

    private String strongestTopic;

    private String weakestTopic;

    private String skillLevel;

    private String improvementArea;

    private ConsistencyDTO consistency;

    // Empty Constructor
    public AnalyticsResponseDTO() {
    }

    // Full Constructor
    public AnalyticsResponseDTO(
            Long totalSubmissions,
            Long acceptedSubmissions,
            Double accuracy,
            Map<String, Long> difficultyStats,
            Map<String, Long> topicStats
    ) {
        this.totalSubmissions = totalSubmissions;
        this.acceptedSubmissions = acceptedSubmissions;
        this.accuracy = accuracy;
        this.difficultyStats = difficultyStats;
        this.topicStats = topicStats;
    }

    // Getters and Setters

    public Long getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(Long totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public Long getAcceptedSubmissions() {
        return acceptedSubmissions;
    }

    public void setAcceptedSubmissions(Long acceptedSubmissions) {
        this.acceptedSubmissions = acceptedSubmissions;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Map<String, Long> getDifficultyStats() {
        return difficultyStats;
    }

    public void setDifficultyStats(Map<String, Long> difficultyStats) {
        this.difficultyStats = difficultyStats;
    }

    public Map<String, Long> getTopicStats() {
        return topicStats;
    }

    public void setTopicStats(Map<String, Long> topicStats) {
        this.topicStats = topicStats;
    }

    public List<String> getWeakTopics() {
        return weakTopics;
    }

    public void setWeakTopics(List<String> weakTopics) {
        this.weakTopics = weakTopics;
    }
    public Double getConsistencyScore() {
        return consistencyScore;
    }

    public void setConsistencyScore(Double consistencyScore) {
        this.consistencyScore = consistencyScore;
    }

    public Map<String, TopicStrengthDTO> getTopicStrength() {
        return topicStrength;
    }
    public void setTopicStrength(
            Map<String, TopicStrengthDTO> topicStrength
    ) {
        this.topicStrength = topicStrength;
    }
    public ActivityStatsDTO getActivityStats() {
        return activityStats;
    }
    public void setActivityStats(
            ActivityStatsDTO activityStats
    ) {
        this.activityStats = activityStats;
    }
    public ConsistencyDTO getConsistency() {
        return consistency;
    }
    public void setConsistency(
            ConsistencyDTO consistency
    ) {
        this.consistency = consistency;
    }
    public String getStrongestTopic() {
        return strongestTopic;
    }

    public void setStrongestTopic(String strongestTopic) {
        this.strongestTopic = strongestTopic;
    }

    public String getWeakestTopic() {
        return weakestTopic;
    }

    public void setWeakestTopic(String weakestTopic) {
        this.weakestTopic = weakestTopic;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getImprovementArea() {
        return improvementArea;
    }

    public void setImprovementArea(String improvementArea) {
        this.improvementArea = improvementArea;
    }
}