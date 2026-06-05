package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.AnalyticsResponseDTO;
import com.saksham.cp_analyzer.service.AnalyticsService;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/{username}")
    public AnalyticsResponseDTO getAnalytics(
            @PathVariable String username
    ) {

        return analyticsService.getUserAnalytics(username);
    }
}