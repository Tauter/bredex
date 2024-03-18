package com.bredex.test.domain.repositories;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.web.dtos.SearchRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT a.* " +
            "FROM Ad a " +
            "WHERE " +
            "a.brand like '%:#{#searchTerm.brand}%' " +
            "OR " +
            "a.type like '%:#{#searchTerm.type}%' " +
            "OR " +
            "a.price = :#{#searchTerm.price} "
    )
    List<Ad> search(SearchRequestDto searchTerm);
}
