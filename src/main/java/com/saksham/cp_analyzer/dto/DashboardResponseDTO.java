package com.saksham.cp_analyzer.dto;

public class DashboardResponseDTO {

    private AnalyticsResponseDTO analytics;

    private Object recentSubmissions;
    private LeetCodeProfileDTO leetcode;
    private String summary;

    private CodeforcesProfileDTO codeforces;

    private RecommendationResponseDTO recommendations;

    public DashboardResponseDTO() {
    }

    public AnalyticsResponseDTO getAnalytics() {
        return analytics;
    }

    public void setAnalytics(
            AnalyticsResponseDTO analytics
    ) {
        this.analytics = analytics;
    }

    public LeetCodeProfileDTO getLeetcode() {
        return leetcode;
    }

    public void setLeetcode(
            LeetCodeProfileDTO leetcode
    ) {
        this.leetcode = leetcode;
    }

    public CodeforcesProfileDTO getCodeforces() {
        return codeforces;
    }

    public void setCodeforces(
            CodeforcesProfileDTO codeforces
    ) {
        this.codeforces = codeforces;
    }

    public RecommendationResponseDTO getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(
            RecommendationResponseDTO recommendations
    ) {
        this.recommendations = recommendations;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Object getRecentSubmissions() {
        return recentSubmissions;
    }

    public void setRecentSubmissions(Object recentSubmissions) {
        this.recentSubmissions = recentSubmissions;
    }
}
