package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.LeetCodeProfileDTO;
import com.saksham.cp_analyzer.service.LeetCodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leetcode")
public class LeetCodeController {

    private final LeetCodeService leetCodeService;

    public LeetCodeController(
            LeetCodeService leetCodeService
    ) {
        this.leetCodeService = leetCodeService;
    }

    @GetMapping("/{username}")
    public LeetCodeProfileDTO getProfile(
            @PathVariable String username
    ) {
        return leetCodeService.getProfile(username);
    }
}
