package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.ProblemDTO;
import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.service.ProblemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // CREATE PROBLEM
    @PostMapping
    public Problem createProblem(@RequestBody ProblemDTO dto) {
        System.out.println("CREATE PROBLEM API HIT");
        return problemService.createProblem(dto);
    }

    // GET ALL PROBLEMS
    @GetMapping
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    // GET PROBLEM BY ID
    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }
}