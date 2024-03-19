package com.bredex.test.services.auth;

import com.bredex.test.domain.models.UserAccount;

public interface ILogOutService {

    void logout(UserAccount userAccount);
}
