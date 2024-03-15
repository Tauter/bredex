package com.bredex.test.domain.repositories;

import com.bredex.test.domain.models.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
@Profile("test")
class UserAccountRepositoryTest {

    @Autowired
    private IUserAccountRepository repository;

    @Test
    void findUserAccountByEmail() {
        Optional<UserAccount> userAccountByEmail = this.repository.findUserAccountByEmail("test@email.com");

        UserAccount userAccount = Assertions.assertDoesNotThrow(userAccountByEmail::get);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, userAccount.getId()),
                () -> Assertions.assertEquals("test@email.com", userAccount.getEmail()),
                () -> Assertions.assertEquals("testName", userAccount.getUserName()),
                () -> Assertions.assertEquals("testPassword", userAccount.getPassword())
        );
    }

    @Test
    void findUserAccountByEmailNotFound() {
        Optional<UserAccount> userAccountByEmail = this.repository.findUserAccountByEmail("notfound");

        Assertions.assertThrows(NoSuchElementException.class, userAccountByEmail::get);
    }

    @Test
    void save() {
        UserAccount toBeSaved = UserAccount.builder().userName("newTestUser").email("newTestEmail").password("newPassword").build();

        UserAccount savedUser = this.repository.save(toBeSaved);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, savedUser.getId()),
                () -> Assertions.assertEquals(savedUser.getUserName(), "newTestUser"),
                () -> Assertions.assertEquals(savedUser.getEmail(), "newTestEmail"),
                () -> Assertions.assertEquals(savedUser.getPassword(), "newPassword")
        );
    }
}
