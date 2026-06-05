package com.saksham.cp_analyzer.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CodeforcesClient {

    private final RestTemplate restTemplate;

    public CodeforcesClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getUserInfo(String handle) {

        String url =
                "https://codeforces.com/api/user.info?handles="
                        + handle;

        return restTemplate.getForObject(
                url,
                String.class
        );
    }
}
