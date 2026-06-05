package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.AnalyticsResponseDTO;
import com.saksham.cp_analyzer.dto.ProblemRecommendationDTO;
import com.saksham.cp_analyzer.dto.RecommendationResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final AnalyticsService analyticsService;
    private final ProblemBankService problemBankService;

    public RecommendationService(
            AnalyticsService analyticsService,
            ProblemBankService problemBankService
    ) {
        this.analyticsService = analyticsService;
        this.problemBankService = problemBankService;
    }

    public RecommendationResponseDTO recommendProblems(
            String username
    ) {

        AnalyticsResponseDTO analytics =
                analyticsService.getUserAnalytics(username);


        List<String> weakTopics =
                analytics.getWeakTopics();

        List<ProblemRecommendationDTO> recommendations =
                new ArrayList<>();

        double accuracy = analytics.getAccuracy();

        for (String topic : weakTopics) {

            List<ProblemRecommendationDTO> topicProblems =
                    problemBankService.getProblemsByTopic(topic);

            for (ProblemRecommendationDTO problem : topicProblems) {

                if (accuracy < 50) {

                    if (problem.getDifficulty().equalsIgnoreCase("Easy")) {
                        recommendations.add(problem);
                    }

                } else if (accuracy < 75) {

                    if (
                            problem.getDifficulty().equalsIgnoreCase("Easy")
                                    ||
                                    problem.getDifficulty().equalsIgnoreCase("Medium")
                    ) {
                        recommendations.add(problem);
                    }

                } else {

                    if (
                            problem.getDifficulty().equalsIgnoreCase("Medium")
                                    ||
                                    problem.getDifficulty().equalsIgnoreCase("Hard")
                    ) {
                        recommendations.add(problem);
                    }
                }
            }
        }
        RecommendationResponseDTO response =
                new RecommendationResponseDTO();

        response.setRecommendedProblems(
                recommendations
        );

        return response;
    }
}
