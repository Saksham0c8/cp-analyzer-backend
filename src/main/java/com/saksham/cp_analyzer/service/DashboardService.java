package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final AnalyticsService analyticsService;
    private final LeetCodeService leetCodeService;
    private final RecommendationService recommendationService;

    public DashboardService(
            AnalyticsService analyticsService,
            LeetCodeService leetCodeService,
            RecommendationService recommendationService
    ) {
        this.analyticsService = analyticsService;
        this.leetCodeService = leetCodeService;
        this.recommendationService = recommendationService;
    }

    public DashboardResponseDTO getDashboard(
            String appUsername,
            String leetcodeUsername
    ) {

        AnalyticsResponseDTO analytics =
                analyticsService.getUserAnalytics(appUsername);

        LeetCodeProfileDTO leetcode =
                leetCodeService.getProfile(leetcodeUsername);

        Object recentSubmissions =
                leetCodeService.getRecentSubmissions(
                        leetcodeUsername
                );

        RecommendationResponseDTO recommendations =
                recommendationService.recommendProblems(appUsername);

        DashboardResponseDTO response =
                new DashboardResponseDTO();

        response.setAnalytics(analytics);
        response.setLeetcode(leetcode);
        response.setCodeforces(null);
        response.setRecommendations(recommendations);
        response.setSummary(generateSummary(analytics));
        response.setRecentSubmissions(recentSubmissions);

        return response;
    }

    private String generateSummary(AnalyticsResponseDTO analytics) {

        String strongest = analytics.getStrongestTopic();
        String weakest = analytics.getWeakestTopic();
        Double consistency = analytics.getConsistencyScore();
        Double accuracy = analytics.getAccuracy();
        String skillLevel = analytics.getSkillLevel();

        StringBuilder summary = new StringBuilder();

        if (skillLevel != null) {
            summary.append("You are currently at ")
                    .append(skillLevel)
                    .append(" level. ");
        }

        if (accuracy != null) {
            summary.append("Your accuracy is ")
                    .append(String.format("%.1f", accuracy))
                    .append("%. ");
        }

        if (strongest != null && !"N/A".equals(strongest)) {
            summary.append("Your strongest topic is ")
                    .append(strongest)
                    .append(". ");
        }

        if (weakest != null && !"N/A".equals(weakest)) {
            summary.append("Focus more on ")
                    .append(weakest)
                    .append(". ");
        }

        if (consistency == null || consistency < 30) {
            summary.append("Your consistency is low, so try solving daily.");
        } else if (consistency < 70) {
            summary.append("Your consistency is improving.");
        } else {
            summary.append("Excellent consistency. Keep the streak going.");
        }

        return summary.toString();
    }
}
