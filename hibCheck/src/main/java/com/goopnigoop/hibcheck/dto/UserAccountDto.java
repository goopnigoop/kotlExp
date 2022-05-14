package com.goopnigoop.hibcheck.dto;

import com.goopnigoop.hibcheck.entities.UserAccount;
import com.goopnigoop.hibcheck.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDto {
    Integer accountId;
    UserDto user;
    BigDecimal balance;
    Currency currency;

    public UserAccountDto(UserAccount userAccount) {
        this.accountId = userAccount.getAccountId();
        this.user = new UserDto(userAccount.getUser());
        this.balance = userAccount.getBalance();
        this.currency = userAccount.getCurrency();
    }



}
