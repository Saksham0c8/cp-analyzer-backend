package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.entity.Problem;
import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.ProblemRepository;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class CodeforcesImportService {

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final RestTemplate restTemplate;

    public CodeforcesImportService(
            UserRepository userRepository,
            ProblemRepository problemRepository,
            SubmissionRepository submissionRepository
    ) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.restTemplate = new RestTemplate();
    }

    public String importSubmissions(
            String appUsername,
            String handle
    ) {

        User user = userRepository.findByUsername(appUsername)
                .orElseThrow(() -> new RuntimeException("App user not found"));

        String url =
                "https://codeforces.com/api/user.status?handle="
                        + handle
                        + "&from=1&count=100";

        Map response =
                restTemplate.getForObject(
                        url,
                        Map.class
                );

        if (response == null || !"OK".equals(response.get("status"))) {
            throw new RuntimeException("Failed to fetch Codeforces submissions");
        }

        List<Map<String, Object>> submissions =
                (List<Map<String, Object>>) response.get("result");

        int importedCount = 0;

        for (Map<String, Object> submissionData : submissions) {

            Map<String, Object> problemData =
                    (Map<String, Object>) submissionData.get("problem");

            if (problemData == null) {
                continue;
            }

            String problemName =
                    (String) problemData.get("name");

            if (problemName == null) {
                continue;
            }

            Problem problem =
                    problemRepository.findByTitle(problemName)
                            .orElseGet(() -> createProblem(problemData));

            Submission submission = new Submission();

            String verdict =
                    (String) submissionData.get("verdict");

            submission.setVerdict(
                    verdict == null ? "UNKNOWN" : verdict
            );

            submission.setLanguage(
                    (String) submissionData.get("programmingLanguage")
            );

            Integer timeConsumedMillis =
                    (Integer) submissionData.get("timeConsumedMillis");

            Integer memoryConsumedBytes =
                    (Integer) submissionData.get("memoryConsumedBytes");

            submission.setRuntime(timeConsumedMillis);
            submission.setMemory(memoryConsumedBytes);

            Integer creationTimeSeconds =
                    (Integer) submissionData.get("creationTimeSeconds");

            if (creationTimeSeconds != null) {

                LocalDateTime submissionTime =
                        LocalDateTime.ofInstant(
                                Instant.ofEpochSecond(creationTimeSeconds.longValue()),
                                ZoneId.systemDefault()
                        );

                submission.setSubmissionTime(submissionTime);
            }

            submission.setUser(user);
            submission.setProblem(problem);

            submissionRepository.save(submission);

            importedCount++;
        }

        return "Imported " + importedCount + " Codeforces submissions for " + handle;
    }

    private Problem createProblem(
            Map<String, Object> problemData
    ) {

        Problem problem = new Problem();

        String name =
                (String) problemData.get("name");

        problem.setTitle(name);

        problem.setPlatform("CODEFORCES");

        Integer rating =
                (Integer) problemData.get("rating");

        if (rating == null) {
            problem.setDifficulty("UNKNOWN");
        } else if (rating <= 1000) {
            problem.setDifficulty("EASY");
        } else if (rating <= 1800) {
            problem.setDifficulty("MEDIUM");
        } else {
            problem.setDifficulty("HARD");
        }

        List<String> tags =
                (List<String>) problemData.get("tags");

        if (tags != null && !tags.isEmpty()) {
            problem.setTopic(tags.get(0));
            problem.setTag(tags.get(0));
        } else {
            problem.setTopic("General");
            problem.setTag("General");
        }

        Object contestId =
                problemData.get("contestId");

        Object index =
                problemData.get("index");

        if (contestId != null && index != null) {
            problem.setProblemUrl(
                    "https://codeforces.com/problemset/problem/"
                            + contestId
                            + "/"
                            + index
            );
        }

        return problemRepository.save(problem);
    }
}
