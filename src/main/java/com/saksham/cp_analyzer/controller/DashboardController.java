package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.DashboardResponseDTO;
import com.saksham.cp_analyzer.service.DashboardService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService =
                dashboardService;
    }

    @GetMapping("/{leetcodeUsername}/{cfHandle}")
    public DashboardResponseDTO getDashboard(

            @PathVariable String leetcodeUsername,

            @PathVariable String cfHandle
    ) {

        return dashboardService.getDashboard(
                leetcodeUsername,
                cfHandle
        );
    }
}
