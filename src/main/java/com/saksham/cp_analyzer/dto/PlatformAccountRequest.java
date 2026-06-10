package com.saksham.cp_analyzer.dto;

import com.saksham.cp_analyzer.entity.Platform;

public class PlatformAccountRequest {

    private Long userId;
    private Platform platform;
    private String username;

    public PlatformAccountRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
