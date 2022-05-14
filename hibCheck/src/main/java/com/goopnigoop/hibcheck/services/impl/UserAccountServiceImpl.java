package com.goopnigoop.hibcheck.services.impl;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import com.goopnigoop.hibcheck.dto.UserDto;
import com.goopnigoop.hibcheck.entities.User;
import com.goopnigoop.hibcheck.entities.UserAccount;
import com.goopnigoop.hibcheck.enums.Currency;
import com.goopnigoop.hibcheck.repositories.UserAccountRepository;
import com.goopnigoop.hibcheck.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@AllArgsConstructor
@Transactional
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccountDto createUserAccountForUser(UserDto userDto, BigDecimal amount, Currency currency) {
        final User user = new User(userDto);
        final UserAccount userAccount = new UserAccount(user, amount, currency);
        return new UserAccountDto(userAccountRepository.save(userAccount));
    }

    @Override
    public UserAccountDto getUserAccountForUser(UserDto user) {
        return new UserAccountDto(userAccountRepository.findByUserId(user.getId()));
    }

    @Override
    public void saveUserAccount(UserAccountDto from) {
        userAccountRepository.save(new UserAccount(from));
    }

    @Override
    public UserAccountDto getUserAccountForUpdate(Integer accountId, Currency currency) {
        return new UserAccountDto(userAccountRepository.selectForUpdateForAccountIdAndCurrency(accountId, currency));
    }

    @Override
    public UserAccountDto getUserAccountForUpdateNative(Integer accountId, Currency currency) {
        return new UserAccountDto(userAccountRepository.selectForUpdateForAccountIdAndCurrencyNative(accountId, currency));
    }

    @Override
    public void clear() {
        userAccountRepository.deleteAll();
    }
}
