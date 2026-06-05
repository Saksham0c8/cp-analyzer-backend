package com.saksham.cp_analyzer.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Problem> problems;

    // getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}