package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.ProblemRepository;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeetCodeSampleDataService {

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;

    public LeetCodeSampleDataService(
            UserRepository userRepository,
            ProblemRepository problemRepository,
            SubmissionRepository submissionRepository
    ) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
    }

    public String importSampleData(String appUsername) {

        User user = userRepository.findByUsername(appUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        createSubmission(user, "Two Sum", "EASY", "Arrays", "ACCEPTED", 1);
        createSubmission(user, "Best Time to Buy and Sell Stock", "EASY", "Arrays", "ACCEPTED", 2);
        createSubmission(user, "Valid Parentheses", "EASY", "Stack", "WRONG_ANSWER", 3);
        createSubmission(user, "Binary Search", "EASY", "Binary Search", "ACCEPTED", 4);
        createSubmission(user, "Search Insert Position", "EASY", "Binary Search", "ACCEPTED", 5);

        createSubmission(user, "Longest Substring Without Repeating Characters", "MEDIUM", "Sliding Window", "WRONG_ANSWER", 6);
        createSubmission(user, "3Sum", "MEDIUM", "Two Pointers", "ACCEPTED", 7);
        createSubmission(user, "Container With Most Water", "MEDIUM", "Two Pointers", "ACCEPTED", 8);
        createSubmission(user, "Coin Change", "MEDIUM", "Dynamic Programming", "WRONG_ANSWER", 9);
        createSubmission(user, "House Robber", "MEDIUM", "Dynamic Programming", "ACCEPTED", 10);

        createSubmission(user, "Merge Intervals", "MEDIUM", "Intervals", "ACCEPTED", 11);
        createSubmission(user, "Number of Islands", "MEDIUM", "Graph", "WRONG_ANSWER", 12);
        createSubmission(user, "Clone Graph", "MEDIUM", "Graph", "ACCEPTED", 13);
        createSubmission(user, "Course Schedule", "MEDIUM", "Graph", "WRONG_ANSWER", 14);
        createSubmission(user, "Kth Largest Element in an Array", "MEDIUM", "Heap", "ACCEPTED", 15);

        createSubmission(user, "Median of Two Sorted Arrays", "HARD", "Binary Search", "WRONG_ANSWER", 16);
        createSubmission(user, "Trapping Rain Water", "HARD", "Two Pointers", "ACCEPTED", 17);
        createSubmission(user, "Word Ladder", "HARD", "Graph", "WRONG_ANSWER", 18);
        createSubmission(user, "Regular Expression Matching", "HARD", "Dynamic Programming", "WRONG_ANSWER", 19);
        createSubmission(user, "Largest Rectangle in Histogram", "HARD", "Stack", "ACCEPTED", 20);

        return "LeetCode sample data imported successfully for " + appUsername;
    }

    private void createSubmission(
            User user,
            String title,
            String difficulty,
            String topic,
            String verdict,
            int daysAgo
    ) {

        Problem problem = problemRepository.findByTitle(title)
                .orElseGet(() -> createProblem(title, difficulty, topic));

        Submission submission = new Submission();

        submission.setUser(user);
        submission.setProblem(problem);
        submission.setVerdict(verdict);
        submission.setLanguage("Java");
        submission.setRuntime(120);
        submission.setMemory(45000);
        submission.setSubmissionTime(LocalDateTime.now().minusDays(daysAgo));

        submissionRepository.save(submission);
    }

    private Problem createProblem(
            String title,
            String difficulty,
            String topic
    ) {

        Problem problem = new Problem();

        problem.setTitle(title);
        problem.setDifficulty(difficulty);
        problem.setTopic(topic);
        problem.setTag(topic);
        problem.setPlatform("LEETCODE");
        problem.setProblemUrl(
                "https://leetcode.com/problems/"
                        + title.toLowerCase()
                        .replace(" ", "-")
                        .replace("(", "")
                        .replace(")", "")
        );

        return problemRepository.save(problem);
    }
}
