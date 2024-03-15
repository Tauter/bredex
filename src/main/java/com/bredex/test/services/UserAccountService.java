package com.bredex.test.services;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.domain.repositories.IUserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService implements IUserAccountService {

    private final IUserAccountRepository userRepository;


    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return this.userRepository.findUserAccountByEmail(email);
    }

    @Override
    public Optional<UserAccount> save(UserAccount userAccount) {
        return Optional.of(this.userRepository.save(userAccount));
    }
}
