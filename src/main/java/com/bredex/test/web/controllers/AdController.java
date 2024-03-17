package com.bredex.test.web.controllers;

import com.bredex.test.domain.mappers.IAdMapper;
import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.services.IAdService;
import com.bredex.test.services.IUserAccountService;
import com.bredex.test.web.dtos.AdCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdController {

    private final IAdService adService;

    private final IUserAccountService userService;

    private final IAdMapper adMapper;

    @PostMapping
    public ResponseEntity<Ad> createAd(@Validated @RequestBody AdCreationDto body, Principal principal) {
        Optional<UserAccount> user = this.userService.findByEmail(principal.getName());

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ad ad = this.adMapper.mapFromCreationDto(body, user.get());

        Optional<Ad> save = this.adService.save(ad);

        return save.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.internalServerError().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, Principal principal) {
        Optional<UserAccount> userAccount = this.userService.findByEmail(principal.getName());

        if (userAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Ad> ad = this.adService.findById(id);

        if (ad.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!ad.get().getUserAccount().equals(userAccount.get())) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        this.adService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getById(@PathVariable Long id) {
        Optional<Ad> ad = this.adService.findById(id);

        return ad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    //todo yes
    @GetMapping("/search")
    public ResponseEntity<List<String>> search() {
        return ResponseEntity.ok(List.of());
    }


}