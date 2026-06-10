package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.client.LeetCodeClient;
import com.saksham.cp_analyzer.dto.LeetCodeStatsDTO;
import com.saksham.cp_analyzer.service.LeetCodeService;
import org.springframework.web.bind.annotation.*;
import com.saksham.cp_analyzer.service.LeetCodeImportService;

@RestController
@RequestMapping("/api/leetcode")
@CrossOrigin(origins = "*")
public class LeetCodeController {

    private final LeetCodeClient leetCodeClient;
    private final LeetCodeService leetCodeService;

    private final LeetCodeImportService leetCodeImportService;

    public LeetCodeController(
            LeetCodeClient leetCodeClient,
            LeetCodeService leetCodeService,
            LeetCodeImportService leetCodeImportService
    ) {
        this.leetCodeClient = leetCodeClient;
        this.leetCodeService = leetCodeService;
        this.leetCodeImportService = leetCodeImportService;
    }

    @GetMapping("/stats/{username}")
    public LeetCodeStatsDTO getStats(
            @PathVariable String username
    ) {
        return leetCodeClient.fetchUserStats(username);
    }

    @GetMapping("/recent/{username}")
    public Object recentSubmissions(
            @PathVariable String username
    ) {
        return leetCodeService.getRecentSubmissions(username);
    }
    @GetMapping("/problem/{slug}")
    public Object problemDetails(
            @PathVariable String slug
    ) {
        return leetCodeService.getProblemDetails(slug);
    }
    @PostMapping("/import/{appUsername}/{leetcodeUsername}")
    public String importRecentLeetCodeSubmissions(
            @PathVariable String appUsername,
            @PathVariable String leetcodeUsername
    ) {
        return leetCodeImportService.importRecentAcceptedSubmissions(
                appUsername,
                leetcodeUsername
        );
    }
}