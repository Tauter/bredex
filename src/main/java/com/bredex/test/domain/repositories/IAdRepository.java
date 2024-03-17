package com.bredex.test.domain.repositories;

import com.bredex.test.domain.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdRepository extends JpaRepository<Ad, Long> {
}
