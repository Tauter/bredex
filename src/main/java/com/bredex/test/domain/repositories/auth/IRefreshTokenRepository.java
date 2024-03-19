package com.bredex.test.domain.repositories.auth;

import com.bredex.test.domain.models.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findRefreshTokenByToken(String token);
}
