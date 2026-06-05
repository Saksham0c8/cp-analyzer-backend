package com.saksham.cp_analyzer.repository;

import com.saksham.cp_analyzer.entity.Submission;
import com.saksham.cp_analyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionRepository
        extends JpaRepository<Submission, Long> {

    List<Submission> findByUser(User user);


    long countByUser(User user);

    long countByUserAndVerdict(User user, String verdict);

    @Query("""
            SELECT s.problem.difficulty, COUNT(s)
            FROM Submission s
            WHERE s.user = :user
            GROUP BY s.problem.difficulty
            """)
    List<Object[]> countSolvedByDifficulty(User user);

    @Query("""
SELECT s.problem.topic, COUNT(s)
FROM Submission s
WHERE s.user = :user
GROUP BY s.problem.topic
""")
    List<Object[]> topicAnalysis(User user);

    @Query("""
SELECT s.problem.topic,
       SUM(CASE WHEN s.verdict = 'ACCEPTED' THEN 1 ELSE 0 END),
       COUNT(s)
FROM Submission s
WHERE s.user = :user
GROUP BY s.problem.topic
""")
    List<Object[]> topicAccuracy(User user);


    @Query("""
SELECT s.problem.topic,
       COUNT(s),
       SUM(
           CASE
               WHEN s.verdict = 'ACCEPTED'
               THEN 1
               ELSE 0
           END
       )
FROM Submission s
WHERE s.user = :user
GROUP BY s.problem.topic
""")
    List<Object[]> topicStrength(User user);

    @Query("""
SELECT COUNT(s)
FROM Submission s
WHERE s.user = :user
AND s.submissionTime >= :date
""")
    Long countSubmissionsAfter(
            User user,
            java.time.LocalDateTime date
    );

    @Query("""
SELECT COUNT(DISTINCT DATE(s.submissionTime))
FROM Submission s
WHERE s.user = :user
""")
    Long countActiveDays(User user);

    @Query("""
SELECT DISTINCT DATE(s.submissionTime)
FROM Submission s
WHERE s.user = :user
ORDER BY DATE(s.submissionTime)
""")
    List<java.sql.Date> findActiveDates(User user);

}
