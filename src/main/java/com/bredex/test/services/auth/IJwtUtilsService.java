package com.bredex.test.services.auth;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.JwtResponseDto;

public interface IJwtUtilsService {
    JwtResponseDto generate(UserAccount userAccount);

    String extractUsername(String token);

    boolean validateToken(String token, UserAccount userAccount);
}
