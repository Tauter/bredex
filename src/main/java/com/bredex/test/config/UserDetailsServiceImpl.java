package com.bredex.test.config;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.domain.repositories.IUserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccountByEmail = this.userAccountRepository.findUserAccountByEmail(username);

        if (userAccountByEmail.isEmpty())
            throw new UsernameNotFoundException("user not found with email: " + username);

        return new User(username, null, List.of());
    }
}
