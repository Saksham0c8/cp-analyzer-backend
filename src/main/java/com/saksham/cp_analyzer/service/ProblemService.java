package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.ProblemDTO;
import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    // CREATE PROBLEM
    public Problem createProblem(ProblemDTO dto) {

        Problem problem = new Problem();

        problem.setTitle(dto.getTitle());
        problem.setDifficulty(dto.getDifficulty());
        problem.setPlatform(dto.getPlatform());
        problem.setTopic(dto.getTopic());
        problem.setProblemUrl(dto.getProblemUrl());

        return problemRepository.save(problem);
    }

    // GET ALL PROBLEMS
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    // GET PROBLEM BY ID
    public Problem getProblemById(Long id) {
        return problemRepository.findById(id).orElse(null);
    }
}