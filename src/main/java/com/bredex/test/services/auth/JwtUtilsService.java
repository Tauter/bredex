package com.bredex.test.services.auth;

import com.bredex.test.config.Properties;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.domain.models.auth.RefreshToken;
import com.bredex.test.domain.repositories.auth.IRefreshTokenRepository;
import com.bredex.test.web.dtos.JwtResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtilsService implements IJwtUtilsService {

    private final IRefreshTokenRepository repository;

    private final Properties properties;

    @Override
    public JwtResponseDto generate(UserAccount userAccount) {

        String jwt = Jwts.builder()
                .setSubject(userAccount.getEmail())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecretKey())
                .compact();

        RefreshToken refreshToken = RefreshToken.builder()
                .userAccount(userAccount)
                .token(UUID.randomUUID().toString())
                .expiryDate(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .build();

        this.repository.save(refreshToken);

        return JwtResponseDto.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .build();

    }

    @Override
    public String extractUsername(String token) {
        Claims body = Jwts.parser().setSigningKey(properties.getSecretKey()).parseClaimsJws(token).getBody();
        Object sub = body.get("sub");
        return sub.toString();
    }

    @Override
    public boolean validateToken(String token, UserAccount userAccount) {
        return false;
    }
}
