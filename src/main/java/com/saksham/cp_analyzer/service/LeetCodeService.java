package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.LeetCodeProfileDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class LeetCodeService {

    public LeetCodeProfileDTO getProfile(String username) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://leetcode.com/graphql";

        String requestBody =
                "{"
                        + "\"query\":\"query getUserProfile($username: String!) { "
                        + "matchedUser(username: $username) { "
                        + "username "
                        + "profile { ranking } "
                        + "submitStats { "
                        + "acSubmissionNum { difficulty count } "
                        + "} "
                        + "} "
                        + "}\","
                        + "\"variables\":{\"username\":\"" + username + "\"}"
                        + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity =
                new HttpEntity<>(requestBody, headers);

        Map response =
                restTemplate.postForObject(
                        url,
                        entity,
                        Map.class
                );

        Map data =
                (Map) response.get("data");

        Map matchedUser =
                (Map) data.get("matchedUser");

        if (matchedUser == null) {
            throw new RuntimeException("LeetCode user not found");
        }

        Map profile =
                (Map) matchedUser.get("profile");

        Map submitStats =
                (Map) matchedUser.get("submitStats");

        List<Map<String, Object>> submissions =
                (List<Map<String, Object>>)
                        submitStats.get("acSubmissionNum");

        int totalSolved = 0;
        int easySolved = 0;
        int mediumSolved = 0;
        int hardSolved = 0;

        for (Map<String, Object> row : submissions) {

            String difficulty =
                    (String) row.get("difficulty");

            Integer count =
                    ((Number) row.get("count")).intValue();

            switch (difficulty) {

                case "All":
                    totalSolved = count;
                    break;

                case "Easy":
                    easySolved = count;
                    break;

                case "Medium":
                    mediumSolved = count;
                    break;

                case "Hard":
                    hardSolved = count;
                    break;
            }
        }


        LeetCodeProfileDTO dto = new LeetCodeProfileDTO();

        dto.setUsername(username);
        dto.setRanking(((Number) profile.get("ranking")).longValue());
        dto.setTotalSolved(totalSolved);
        dto.setEasySolved(easySolved);
        dto.setMediumSolved(mediumSolved);
        dto.setHardSolved(hardSolved);
        System.out.println("LeetCode API Response Processed Successfully");

        return dto;
    }
}