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
@EqualsAndHashCode
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String token;

    private Date expiryDate;

    @ManyToOne
    private UserAccount userAccount;

}
