package com.saksham.cp_analyzer.dto;

import java.util.List;

public class RecommendationResponseDTO {

    private List<ProblemRecommendationDTO> recommendedProblems;

    public RecommendationResponseDTO() {
    }

    public RecommendationResponseDTO(
            List<ProblemRecommendationDTO> recommendedProblems
    ) {
        this.recommendedProblems = recommendedProblems;
    }

    public List<ProblemRecommendationDTO> getRecommendedProblems() {
        return recommendedProblems;
    }

    public void setRecommendedProblems(
            List<ProblemRecommendationDTO> recommendedProblems
    ) {
        this.recommendedProblems = recommendedProblems;
    }
}
