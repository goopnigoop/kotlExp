package com.goopnigoop.hibcheck.services.impl;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import com.goopnigoop.hibcheck.services.TransferService;
import com.goopnigoop.hibcheck.services.UserAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Qualifier("transferService")
public class TransferServiceImpl implements TransferService {

    private final UserAccountService userAccountService;

    public TransferServiceImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public Consumer<UserAccountDto> getUserAccountDtoConsumer(UserAccountService userAccountService) {
        return account -> userAccountService.getUserAccountForUpdate(account.getAccountId(), account.getCurrency());
    }

    @Override
    public UserAccountService getUserAccountService() {
        return userAccountService;
    }
}
