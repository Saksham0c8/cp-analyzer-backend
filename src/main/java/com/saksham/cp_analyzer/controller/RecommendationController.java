package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.RecommendationResponseDTO;
import com.saksham.cp_analyzer.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService
    ) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{username}")
    public RecommendationResponseDTO getRecommendations(
            @PathVariable String username
    ) {

        return recommendationService
                .recommendProblems(username);
    }
}
