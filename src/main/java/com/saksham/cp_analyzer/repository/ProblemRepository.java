package com.saksham.cp_analyzer.repository;

import com.saksham.cp_analyzer.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByTag(String tag);
}
