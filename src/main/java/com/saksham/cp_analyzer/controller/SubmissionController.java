package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(
            SubmissionService submissionService
    ) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public Submission createSubmission(
            @RequestParam Long userId,
            @RequestParam Long problemId,
            @RequestBody Submission submission
    ) {

        return submissionService.createSubmission(
                userId,
                problemId,
                submission
        );
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(
            @PathVariable Long id
    ) {
        return submissionService.getSubmissionById(id);
    }
}
