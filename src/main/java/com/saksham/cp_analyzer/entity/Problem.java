package com.saksham.cp_analyzer.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String problemUrl;

    private String difficulty;
    private String tag;

    private String platform;

    private String topic;


    @ManyToMany
    @JoinTable(
            name = "problem_tags",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    // getters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getProblemUrl() {
        return problemUrl;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTopic() {
        return topic;
    }

    public List<Tag> getTags() {
        return tags;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setProblemUrl(String problemUrl) {
        this.problemUrl = problemUrl;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}