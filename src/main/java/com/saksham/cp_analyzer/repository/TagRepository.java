package com.saksham.cp_analyzer.repository;

import com.saksham.cp_analyzer.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TagRepository extends JpaRepository<Tag, Long> {
}
