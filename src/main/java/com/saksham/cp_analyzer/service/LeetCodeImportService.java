package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.ProblemRepository;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class LeetCodeImportService {

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final LeetCodeService leetCodeService;

    public LeetCodeImportService(
            UserRepository userRepository,
            ProblemRepository problemRepository,
            SubmissionRepository submissionRepository,
            LeetCodeService leetCodeService
    ) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.leetCodeService = leetCodeService;
    }

    public String importRecentAcceptedSubmissions(
            String appUsername,
            String leetcodeUsername
    ) {

        User user = userRepository.findByUsername(appUsername)
                .orElseThrow(() -> new RuntimeException("App user not found"));

        Map response =
                (Map) leetCodeService.getRecentSubmissions(leetcodeUsername);

        Map data =
                (Map) response.get("data");

        List<Map<String, Object>> recentSubmissions =
                (List<Map<String, Object>>) data.get("recentAcSubmissionList");

        int imported = 0;
        int skipped = 0;

        for (Map<String, Object> item : recentSubmissions) {

            String title =
                    (String) item.get("title");

            String titleSlug =
                    (String) item.get("titleSlug");

            String timestamp =
                    (String) item.get("timestamp");

            if (title == null || titleSlug == null || timestamp == null) {
                skipped++;
                continue;
            }

            Problem problem =
                    problemRepository.findByTitle(title)
                            .orElseGet(() -> createProblemFromLeetCode(
                                    title,
                                    titleSlug
                            ));

            boolean alreadyExists =
                    submissionRepository.existsByUserAndProblem(
                            user,
                            problem
                    );

            if (alreadyExists) {
                skipped++;
                continue;
            }

            Submission submission = new Submission();

            submission.setUser(user);
            submission.setProblem(problem);
            submission.setVerdict("ACCEPTED");
            submission.setLanguage("Unknown");
            submission.setRuntime(0);
            submission.setMemory(0);

            LocalDateTime submissionTime =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(
                                    Long.parseLong(timestamp)
                            ),
                            ZoneId.systemDefault()
                    );

            submission.setSubmissionTime(submissionTime);

            submissionRepository.save(submission);

            imported++;
        }

        return "Imported " + imported + " real LeetCode submissions. Skipped " + skipped + " duplicates/invalid entries.";
    }

    private Problem createProblemFromLeetCode(
            String title,
            String titleSlug
    ) {

        Map response =
                (Map) leetCodeService.getProblemDetails(titleSlug);

        Map data =
                (Map) response.get("data");

        Map question =
                (Map) data.get("question");

        String difficulty = "UNKNOWN";
        String topic = "General";

        if (question != null) {

            Object diff =
                    question.get("difficulty");

            if (diff != null) {
                difficulty =
                        diff.toString().toUpperCase();
            }

            List<Map<String, Object>> topicTags =
                    (List<Map<String, Object>>)
                            question.get("topicTags");

            if (topicTags != null && !topicTags.isEmpty()) {

                Object tagName =
                        topicTags.get(0).get("name");

                if (tagName != null) {
                    topic =
                            tagName.toString();
                }
            }
        }

        Problem problem = new Problem();

        problem.setTitle(title);
        problem.setDifficulty(difficulty);
        problem.setTopic(topic);
        problem.setTag(topic);
        problem.setPlatform("LEETCODE");
        problem.setProblemUrl(
                "https://leetcode.com/problems/" + titleSlug
        );

        return problemRepository.save(problem);
    }
}
