package com.saksham.cp_analyzer.dto;

public class RegisterRequestDTO {

    private String name;

    private String email;

    private String username;

    private String password;

    // Empty Constructor
    public RegisterRequestDTO() {
    }

    // Full Constructor
    public RegisterRequestDTO(
            String name,
            String email,
            String username,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
