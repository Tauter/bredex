package com.bredex.test.services;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.web.dtos.SearchRequestDto;

import java.util.List;
import java.util.Optional;

public interface IAdService {
    Optional<Ad> save(Ad toBeSaved);

    void delete(Long id);

    Optional<Ad> findById(Long id);

    List<Ad> search(SearchRequestDto searchTerm);


}
