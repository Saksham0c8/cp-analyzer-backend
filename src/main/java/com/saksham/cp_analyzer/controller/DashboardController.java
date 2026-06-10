package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.DashboardResponseDTO;
import com.saksham.cp_analyzer.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{appUsername}/{leetcodeUsername}")
    public DashboardResponseDTO getDashboard(
            @PathVariable String appUsername,
            @PathVariable String leetcodeUsername
    ) {
        return dashboardService.getDashboard(
                appUsername,
                leetcodeUsername
        );
    }
}