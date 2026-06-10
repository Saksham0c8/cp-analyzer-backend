package com.saksham.cp_analyzer.repository;

import com.saksham.cp_analyzer.entity.Platform;
import com.saksham.cp_analyzer.entity.PlatformAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformAccountRepository extends JpaRepository<PlatformAccount, Long> {

    Optional<PlatformAccount> findByPlatformAndUsername(
            Platform platform,
            String username
    );

    Optional<PlatformAccount> findByUserIdAndPlatform(
            Long userId,
            Platform platform
    );
}
