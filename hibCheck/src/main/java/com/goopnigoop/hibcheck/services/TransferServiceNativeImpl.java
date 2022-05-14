package com.goopnigoop.hibcheck.services;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Qualifier("nativeTransferService")
public class TransferServiceNativeImpl implements TransferService {

    private final UserAccountService userAccountService;

    public TransferServiceNativeImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public Consumer<UserAccountDto> getUserAccountDtoConsumer(UserAccountService userAccountService) {
        return account -> userAccountService.getUserAccountForUpdateNative(account.getAccountId(), account.getCurrency());
    }

    @Override
    public UserAccountService getUserAccountService() {
        return userAccountService;
    }
}
