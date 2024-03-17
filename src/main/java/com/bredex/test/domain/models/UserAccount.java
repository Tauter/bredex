package com.bredex.test.domain.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String password;

    @OneToMany
    private Set<Ad> ads;

}
