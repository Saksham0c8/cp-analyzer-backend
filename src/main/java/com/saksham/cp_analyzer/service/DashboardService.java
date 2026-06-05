package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.*;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final AnalyticsService analyticsService;

    private final LeetCodeService leetCodeService;

    private final CodeforcesService codeforcesService;

    private final RecommendationService recommendationService;

    public DashboardService(
            AnalyticsService analyticsService,
            LeetCodeService leetCodeService,
            CodeforcesService codeforcesService,
            RecommendationService recommendationService
    ) {
        this.analyticsService = analyticsService;
        this.leetCodeService = leetCodeService;
        this.codeforcesService = codeforcesService;
        this.recommendationService = recommendationService;
    }

    public DashboardResponseDTO getDashboard(
            String username,
            String codeforcesHandle
    ) {

        AnalyticsResponseDTO analytics =
                analyticsService.getUserAnalytics(
                        username
                );

        LeetCodeProfileDTO leetcode =
                leetCodeService.getProfile(
                        username
                );

        CodeforcesProfileDTO codeforces =
                codeforcesService.getProfile(
                        codeforcesHandle
                );

        RecommendationResponseDTO recommendations =
                recommendationService.recommendProblems(
                        username
                );
        String summary =
                generateSummary(
                        analytics
                );

        DashboardResponseDTO response =
                new DashboardResponseDTO();

        response.setAnalytics(
                analytics
        );

        response.setLeetcode(
                leetcode
        );

        response.setCodeforces(
                codeforces
        );

        response.setRecommendations(
                recommendations
        );
        response.setSummary(summary);
        return response;
    }
    private String generateSummary(
            AnalyticsResponseDTO analytics
    ) {

        String strongest =
                analytics.getStrongestTopic();

        String weakest =
                analytics.getWeakestTopic();

        Double consistency =
                analytics.getConsistencyScore();

        StringBuilder summary =
                new StringBuilder();

        if (strongest != null) {

            summary.append(
                    "Strong in "
                            + strongest
                            + ". "
            );
        }

        if (weakest != null) {

            summary.append(
                    "Needs improvement in "
                            + weakest
                            + ". "
            );
        }

        if (consistency < 30) {

            summary.append(
                    "Consistency is low. Practice daily."
            );

        } else if (consistency < 70) {

            summary.append(
                    "Consistency is improving."
            );

        } else {

            summary.append(
                    "Excellent consistency."
            );
        }

        return summary.toString();
    }
}
