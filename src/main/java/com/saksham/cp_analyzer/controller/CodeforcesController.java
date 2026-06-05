package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.CodeforcesProfileDTO;
import com.saksham.cp_analyzer.service.CodeforcesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codeforces")
public class CodeforcesController {

    private final CodeforcesService codeforcesService;

    public CodeforcesController(
            CodeforcesService codeforcesService
    ) {
        this.codeforcesService =
                codeforcesService;
    }

    @GetMapping("/{handle}")
    public CodeforcesProfileDTO getProfile(
            @PathVariable String handle
    ) {
        return codeforcesService
                .getProfile(handle);
    }
}
