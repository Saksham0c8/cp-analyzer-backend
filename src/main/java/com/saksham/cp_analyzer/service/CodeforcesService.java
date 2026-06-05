package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.CodeforcesProfileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CodeforcesService {

    public CodeforcesProfileDTO getProfile(
            String handle
    ) {

        RestTemplate restTemplate =
                new RestTemplate();

        String userInfoUrl =
                "https://codeforces.com/api/user.info?handles="
                        + handle;

        Map userResponse =
                restTemplate.getForObject(
                        userInfoUrl,
                        Map.class
                );

        List<Map<String, Object>> result =
                (List<Map<String, Object>>)
                        userResponse.get("result");

        Map<String, Object> user =
                result.get(0);

        Integer currentRating =
                user.get("rating") == null
                        ? 0
                        : ((Number) user.get("rating")).intValue();

        Integer maxRating =
                user.get("maxRating") == null
                        ? 0
                        : ((Number) user.get("maxRating")).intValue();

        String ratingUrl =
                "https://codeforces.com/api/user.rating?handle="
                        + handle;

        Map ratingResponse =
                restTemplate.getForObject(
                        ratingUrl,
                        Map.class
                );

        List<Map<String, Object>> contests =
                (List<Map<String, Object>>)
                        ratingResponse.get("result");

        int contestsParticipated =
                contests.size();

        int bestRank =
                Integer.MAX_VALUE;

        int firstRating = 0;

        for (Map<String, Object> contest : contests) {

            int rank =
                    ((Number) contest.get("rank"))
                            .intValue();

            bestRank =
                    Math.min(
                            bestRank,
                            rank
                    );
        }

        if (!contests.isEmpty()) {

            firstRating =
                    ((Number) contests.get(0)
                            .get("oldRating"))
                            .intValue();
        }

        int ratingGrowth =
                currentRating - firstRating;

        CodeforcesProfileDTO dto =
                new CodeforcesProfileDTO();

        dto.setHandle(handle);
        dto.setCurrentRating(currentRating);
        dto.setMaxRating(maxRating);
        dto.setContestsParticipated(
                contestsParticipated
        );
        dto.setBestRank(bestRank);
        dto.setRatingGrowth(
                ratingGrowth
        );

        return dto;
    }
}
