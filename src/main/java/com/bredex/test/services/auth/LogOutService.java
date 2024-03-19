package com.bredex.test.services.auth;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.domain.models.auth.Logout;
import com.bredex.test.domain.repositories.auth.ILogOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LogOutService implements ILogOutService {

    private final ILogOutRepository repository;

    @Override
    public void logout(UserAccount userAccount) {
        this.repository.save(
                Logout.builder()
                        .loggedOut(Date
                                .from(Instant.now()))
                        .userAccount(userAccount)
                        .build()
        );
    }
}
