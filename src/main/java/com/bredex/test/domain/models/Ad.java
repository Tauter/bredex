package com.bredex.test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ad {

    @Id
    Long id;

    @ManyToOne
    private UserAccount userAccount;

    private String brand;

    private String type;

    private String description;

    private Long price;

}
