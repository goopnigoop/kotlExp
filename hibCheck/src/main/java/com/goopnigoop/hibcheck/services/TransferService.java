package com.goopnigoop.hibcheck.services;

import com.goopnigoop.hibcheck.dto.UserAccountDto;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface TransferService {
    @Transactional(rollbackOn = Exception.class)
    default void transfer(UserAccountDto from, UserAccountDto to, BigDecimal amount) {
        if (Optional.ofNullable(amount).filter
                (value -> BigDecimal.ZERO.compareTo(value) < 0).isEmpty()) {
            throw new IllegalArgumentException("Amount value should be more than zero");
        }
        final UserAccountService userAccountService = getUserAccountService();
        Stream.of(from, to)
              .sorted(Comparator.comparing(UserAccountDto::getAccountId))
              .forEachOrdered(getUserAccountDtoConsumer(userAccountService));

        final BigDecimal accountBalanceFrom = getAccountBalance(from);
        final BigDecimal accountBalanceTo = getAccountBalance(to);
        if (accountBalanceFrom.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Amount can not be less the balance of source account");
        }

        from.setBalance(accountBalanceFrom.subtract(amount));
        to.setBalance(accountBalanceTo.add(amount));
        userAccountService.saveUserAccount(from);
        userAccountService.saveUserAccount(to);
    }

    Consumer<UserAccountDto> getUserAccountDtoConsumer(UserAccountService userAccountService);

    private BigDecimal getAccountBalance(UserAccountDto from) {
        return Optional.ofNullable(from).map(UserAccountDto::getBalance)
                       .orElseThrow(() -> new IllegalArgumentException("Cannot get an balance from source account"));
    }

    UserAccountService getUserAccountService();
}
