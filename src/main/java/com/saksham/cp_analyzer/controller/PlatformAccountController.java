package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.PlatformAccountRequest;
import com.saksham.cp_analyzer.entity.PlatformAccount;
import com.saksham.cp_analyzer.service.PlatformAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platform")
@CrossOrigin(origins = "*")
public class PlatformAccountController {

    private final PlatformAccountService platformAccountService;

    public PlatformAccountController(PlatformAccountService platformAccountService) {
        this.platformAccountService = platformAccountService;
    }

    @PostMapping("/link")
    public PlatformAccount linkPlatformAccount(@RequestBody PlatformAccountRequest request) {
        return platformAccountService.linkPlatformAccount(
                request.getUserId(),
                request.getPlatform(),
                request.getUsername()
        );
    }
}
