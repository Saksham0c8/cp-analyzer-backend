package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.service.LeetCodeSampleDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample-data")
@CrossOrigin(origins = "*")
public class LeetCodeSampleDataController {

    private final LeetCodeSampleDataService leetCodeSampleDataService;

    public LeetCodeSampleDataController(
            LeetCodeSampleDataService leetCodeSampleDataService
    ) {
        this.leetCodeSampleDataService = leetCodeSampleDataService;
    }

    @PostMapping("/leetcode/{appUsername}")
    public String importLeetCodeSampleData(
            @PathVariable String appUsername
    ) {
        return leetCodeSampleDataService.importSampleData(appUsername);
    }
}
