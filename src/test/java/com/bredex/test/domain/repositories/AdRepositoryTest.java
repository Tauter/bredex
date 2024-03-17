package com.bredex.test.domain.repositories;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@Profile("test")
class AdRepositoryTest {

    @Autowired
    private IAdRepository repository;

    @Autowired
    private IUserAccountRepository userRepository;

    @Test
    void save() {
        int savedAdsCount = this.repository.findAll().size();

        UserAccount user = userRepository.findAll().get(0);
        Ad toBeSaved = Ad.builder().brand("Skoda").type("Superb").description("2.0 TDI").price(Long.valueOf("1000")).userAccount(user).build();
        Ad saved = this.repository.save(toBeSaved);

        Assertions.assertEquals(savedAdsCount + 1, this.repository.findAll().size());

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(toBeSaved.getBrand(), saved.getBrand());
        Assertions.assertEquals(toBeSaved.getType(), saved.getType());
        Assertions.assertEquals(toBeSaved.getDescription(), saved.getDescription());
        Assertions.assertEquals(toBeSaved.getPrice(), saved.getPrice());
        Assertions.assertEquals(user, saved.getUserAccount());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        int savedAdsCount = this.repository.findAll().size();

        this.repository.deleteById(1L);

        int expectedAdsCount = this.repository.findAll().size() + 1;

        Assertions.assertEquals(expectedAdsCount, savedAdsCount);
    }

    @Test
    void deleteByIdNotFound() {
        int savedAdsCount = this.repository.findAll().size();

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> this.repository.deleteById(0L));

        int expectedAdsCount = this.repository.findAll().size();

        Assertions.assertEquals(expectedAdsCount, savedAdsCount);
    }

    @Test
    void findById() {

        Ad ad = Assertions.assertDoesNotThrow(() -> this.repository.findById(1L).get());

        Assertions.assertEquals(1L, ad.getId());
        Assertions.assertEquals("Skoda", ad.getBrand());
        Assertions.assertEquals("Superb", ad.getType());
        Assertions.assertEquals("2.0 TDI", ad.getDescription());

    }

    @Test
    void findByIdNotFound() {
        Assertions.assertTrue(this.repository.findById(0L).isEmpty());
    }


}
