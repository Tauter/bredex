package com.bredex.test.services;

import com.bredex.test.domain.models.UserAccount;

import java.util.Optional;

public interface IUserAccountService {
    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> save(UserAccount userAccount);
}
