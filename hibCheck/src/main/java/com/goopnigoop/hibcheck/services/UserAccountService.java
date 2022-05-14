package com.goopnigoop.hibcheck.services;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import com.goopnigoop.hibcheck.dto.UserDto;
import com.goopnigoop.hibcheck.enums.Currency;

import java.math.BigDecimal;

public interface UserAccountService {
    UserAccountDto createUserAccountForUser(UserDto userDto, BigDecimal amount, Currency currency);

    UserAccountDto getUserAccountForUser(UserDto userFirst);

    void saveUserAccount(UserAccountDto from);

    UserAccountDto getUserAccountForUpdate(Integer accountId, Currency currency);

    UserAccountDto getUserAccountForUpdateNative(Integer accountId, Currency currency);

    void clear();
}
