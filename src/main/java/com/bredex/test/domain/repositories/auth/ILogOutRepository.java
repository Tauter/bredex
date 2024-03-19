package com.bredex.test.domain.repositories.auth;

import com.bredex.test.domain.models.auth.Logout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogOutRepository extends JpaRepository<Logout, Long> {
}
