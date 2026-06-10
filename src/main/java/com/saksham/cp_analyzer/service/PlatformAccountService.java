package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.entity.Platform;
import com.saksham.cp_analyzer.entity.PlatformAccount;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.PlatformAccountRepository;
import com.saksham.cp_analyzer.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.saksham.cp_analyzer.client.LeetCodeClient;
import com.saksham.cp_analyzer.dto.LeetCodeStatsDTO;

@Service
public class PlatformAccountService {

    private final PlatformAccountRepository platformAccountRepository;
    private final UserRepository userRepository;

    private final LeetCodeClient leetCodeClient;

    public PlatformAccountService(
            PlatformAccountRepository platformAccountRepository,
            UserRepository userRepository,
            LeetCodeClient leetCodeClient
    ) {
        this.platformAccountRepository = platformAccountRepository;
        this.userRepository = userRepository;
        this.leetCodeClient = leetCodeClient;
    }

    public PlatformAccount linkPlatformAccount(Long userId, Platform platform, String username) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlatformAccount platformAccount =
                platformAccountRepository.findByUserIdAndPlatform(userId, platform)
                        .orElse(new PlatformAccount());

        platformAccount.setPlatform(platform);
        platformAccount.setUsername(username);
        platformAccount.setUser(user);

        PlatformAccount savedAccount = platformAccountRepository.save(platformAccount);

        if (platform == Platform.LEETCODE) {

            LeetCodeStatsDTO stats =
                    leetCodeClient.fetchUserStats(username);

            user.setTotalSolved(stats.getTotalSolved());

            user.setEasySolved(stats.getEasySolved());

            user.setMediumSolved(stats.getMediumSolved());

            user.setHardSolved(stats.getHardSolved());

            user.setRating(stats.getRanking());

            userRepository.save(user);
        }

        return savedAccount;
    }
}
