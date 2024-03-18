package com.bredex.test.services;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.repositories.IAdRepository;
import com.bredex.test.web.dtos.SearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdService implements IAdService {

    private final IAdRepository adRepository;

    @Override
    public Optional<Ad> save(Ad toBeSaved) {
        return Optional.of(this.adRepository.save(toBeSaved));
    }

    @Override
    public void delete(Long id) {
        this.adRepository.deleteById(id);
    }

    @Override
    public Optional<Ad> findById(Long id) {
        return this.adRepository.findById(id);
    }

    @Override
    public List<Ad> search(SearchRequestDto searchTerm) {
        return this.adRepository.search(searchTerm);
    }
}
