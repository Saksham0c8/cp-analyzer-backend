package com.saksham.cp_analyzer.client;

import com.saksham.cp_analyzer.dto.LeetCodeStatsDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class LeetCodeClient {

    private final RestTemplate restTemplate;

    public LeetCodeClient() {
        this.restTemplate = new RestTemplate();
    }

    public LeetCodeStatsDTO fetchUserStats(String username) {

        String url = "https://leetcode.com/graphql";

        String query = """
                query getUserProfile($username: String!) {
                  matchedUser(username: $username) {
                    username
                    profile {
                      ranking
                    }
                    submitStats {
                      acSubmissionNum {
                        difficulty
                        count
                      }
                    }
                  }
                }
                """;

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("username", username)
        );

        Map response = restTemplate.postForObject(
                url,
                body,
                Map.class
        );

        Map data = (Map) response.get("data");
        Map matchedUser = (Map) data.get("matchedUser");

        if (matchedUser == null) {
            throw new RuntimeException("LeetCode user not found");
        }

        Map profile = (Map) matchedUser.get("profile");
        Integer ranking = (Integer) profile.get("ranking");

        Map submitStats = (Map) matchedUser.get("submitStats");
        List<Map<String, Object>> acSubmissionNum =
                (List<Map<String, Object>>) submitStats.get("acSubmissionNum");

        int totalSolved = 0;
        int easySolved = 0;
        int mediumSolved = 0;
        int hardSolved = 0;

        for (Map<String, Object> item : acSubmissionNum) {

            String difficulty = (String) item.get("difficulty");
            Integer count = (Integer) item.get("count");

            if ("All".equals(difficulty)) {
                totalSolved = count;
            } else if ("Easy".equals(difficulty)) {
                easySolved = count;
            } else if ("Medium".equals(difficulty)) {
                mediumSolved = count;
            } else if ("Hard".equals(difficulty)) {
                hardSolved = count;
            }
        }

        return new LeetCodeStatsDTO(
                username,
                totalSolved,
                easySolved,
                mediumSolved,
                hardSolved,
                ranking == null ? 0 : ranking
        );
    }
}