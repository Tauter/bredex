package com.bredex.test.domain.repositories;

import com.bredex.test.domain.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findUserAccountByEmail(String email);
}
