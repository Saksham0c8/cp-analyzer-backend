package com.saksham.cp_analyzer.dto;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String username;

    private Integer rating;

    private Integer totalSolved;

    // Empty Constructor
    public UserDTO() {
    }

    // Full Constructor
    public UserDTO(
            Long id,
            String name,
            String email,
            String username,
            Integer rating,
            Integer totalSolved
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.rating = rating;
        this.totalSolved = totalSolved;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(Integer totalSolved) {
        this.totalSolved = totalSolved;
    }
}