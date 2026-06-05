package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.SubmissionDTO;
import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.ProblemRepository;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    public SubmissionService(
            SubmissionRepository submissionRepository,
            UserRepository userRepository,
            ProblemRepository problemRepository
    ) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    public Submission createSubmission(
            Long userId,
            Long problemId,
            Submission submission
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        submission.setUser(user);
        submission.setProblem(problem);

        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }
}
