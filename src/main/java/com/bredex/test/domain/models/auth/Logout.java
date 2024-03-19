package com.bredex.test.domain.models.auth;

import com.bredex.test.domain.models.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Logout {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date loggedOut;

    @ManyToOne
    private UserAccount userAccount;
}
