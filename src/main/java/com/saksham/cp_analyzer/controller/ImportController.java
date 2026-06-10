package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.service.CodeforcesImportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/import")
@CrossOrigin(origins = "*")
public class ImportController {

    private final CodeforcesImportService codeforcesImportService;

    public ImportController(
            CodeforcesImportService codeforcesImportService
    ) {
        this.codeforcesImportService = codeforcesImportService;
    }

    @PostMapping("/codeforces")
    public String importCodeforcesSubmissions(
            @RequestParam String appUsername,
            @RequestParam String handle
    ) {
        return codeforcesImportService.importSubmissions(
                appUsername,
                handle
        );
    }
}
