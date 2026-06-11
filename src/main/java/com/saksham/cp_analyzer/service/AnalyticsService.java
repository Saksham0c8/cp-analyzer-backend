package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.AnalyticsResponseDTO;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import com.saksham.cp_analyzer.dto.TopicStrengthDTO;
import java.time.LocalDateTime;
import com.saksham.cp_analyzer.dto.ActivityStatsDTO;
import com.saksham.cp_analyzer.dto.ConsistencyDTO;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service

public class AnalyticsService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    public AnalyticsService(SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }


    public AnalyticsResponseDTO getUserAnalytics(String username) {

        // Find User
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Total submissions
        long totalSubmissions = submissionRepository.countByUser(user);
        Long activeDays =
                submissionRepository.countActiveDays(user);

        List<LocalDate> activeDates =
                submissionRepository.findActiveDates(user);
        Long last7Days =
                submissionRepository.countSubmissionsAfter(
                        user,
                        LocalDateTime.now().minusDays(7)
                );

        Long last30Days =
                submissionRepository.countSubmissionsAfter(
                        user,
                        LocalDateTime.now().minusDays(30)
                );

        int longestStreak = 0;
        int currentStreak = 1;

        for (int i = 1; i < activeDates.size(); i++) {

            if (
                    activeDates.get(i - 1)
                            .plusDays(1)
                            .equals(activeDates.get(i))
            ) {

                currentStreak++;

            } else {

                longestStreak =
                        Math.max(
                                longestStreak,
                                currentStreak
                        );

                currentStreak = 1;
            }
        }

        if (!activeDates.isEmpty()) {

            longestStreak =
                    Math.max(
                            longestStreak,
                            currentStreak
                    );
        }

        // Accepted submissions
        long acceptedSubmissions =
                submissionRepository.countByUserAndVerdict(
                        user,
                        "ACCEPTED"
                );

        // Overall Accuracy
        double accuracy = 0;

        if (totalSubmissions > 0) {
            accuracy =
                    ((double) acceptedSubmissions / totalSubmissions) * 100;
        }
        double consistencyScore =
                Math.min(
                        100,
                        (activeDays * 3)
                                +
                                (longestStreak * 5)
                                +
                                (last30Days * 0.5)
                );
        String skillLevel;

        if (accuracy < 40) {

            skillLevel = "Beginner";

        }
        else if (accuracy < 70) {

            skillLevel = "Intermediate";

        }
        else {

            skillLevel = "Advanced";
        }

        // Difficulty Stats
        List<Object[]> difficultyData =
                submissionRepository.countSolvedByDifficulty(user);

        Map<String, Long> difficultyStats = new HashMap<>();

        for (Object[] row : difficultyData) {

            String difficulty = (String) row[0];
            Long count = ((Number) row[1]).longValue();

            difficultyStats.put(difficulty, count);
        }

        // Topic Stats
        List<Object[]> topicData =
                submissionRepository.topicAnalysis(user);

        Map<String, Long> topicStats = new HashMap<>();

        for (Object[] row : topicData) {

            String topic = (String) row[0];
            Long count = ((Number) row[1]).longValue();

            topicStats.put(topic, count);
        }
        String strongestTopic = "N/A";

        double strongestAccuracy = -1;

        String weakestTopic = "N/A";

        double weakestAccuracy = 101;
        Map<String, TopicStrengthDTO> topicStrength =
                new HashMap<>();

        List<Object[]> strengthData =
                submissionRepository.topicStrength(user);

        for (Object[] row : strengthData) {

            String topic =
                    (String) row[0];

            Long attempted =
                    ((Number) row[1]).longValue();

            Long solved =
                    ((Number) row[2]).longValue();

            double strengthAccuracy = 0;

            if (attempted > 0) {

                strengthAccuracy =
                        ((double) solved / attempted) * 100;
            }

            if (strengthAccuracy > strongestAccuracy) {

                strongestAccuracy =
                        strengthAccuracy;

                strongestTopic =
                        topic;
            }

            if (strengthAccuracy < weakestAccuracy) {

                weakestAccuracy =
                        strengthAccuracy;

                weakestTopic =
                        topic;
            }

            topicStrength.put(
                    topic,
                    new TopicStrengthDTO(
                            attempted,
                            solved,
                            strengthAccuracy
                    )
            );
        }

        // Weak Topics
        List<Object[]> topicAccuracyData =
                submissionRepository.topicAccuracy(user);

        List<String> weakTopics = new ArrayList<>();

        for (Object[] row : topicAccuracyData) {

            String topic = (String) row[0];

            Long accepted =
                    ((Number) row[1]).longValue();

            Long total =
                    ((Number) row[2]).longValue();

            double topicAccuracy = 0;

            if (total > 0) {
                topicAccuracy =
                        ((double) accepted / total) * 100;
            }

            if (topicAccuracy < 60) {
                weakTopics.add(topic);
            }
        }

        // Return DTO
        AnalyticsResponseDTO response =
                new AnalyticsResponseDTO(
                        totalSubmissions,
                        acceptedSubmissions,
                        accuracy,
                        difficultyStats,
                        topicStats
                );

        ConsistencyDTO consistencyDTO =
                new ConsistencyDTO(
                        activeDays,
                        longestStreak,
                        consistencyScore
                );
        ActivityStatsDTO activityStats =
                new ActivityStatsDTO(
                        last7Days,
                        last30Days,
                        activeDays
                );

        response.setWeakTopics(weakTopics);
        response.setConsistencyScore(consistencyScore);

        response.setTopicStrength(
                topicStrength
        );

        response.setActivityStats(
                activityStats
        );

        response.setConsistency(
                consistencyDTO
        );

        response.setStrongestTopic(
                strongestTopic
        );

        response.setWeakestTopic(
                weakestTopic
        );

        response.setSkillLevel(
                skillLevel
        );

        response.setImprovementArea(
                weakestTopic
        );

        return response;
    }
}
