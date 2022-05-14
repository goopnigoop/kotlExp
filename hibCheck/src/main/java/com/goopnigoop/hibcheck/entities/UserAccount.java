package com.goopnigoop.hibcheck.entities;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import com.goopnigoop.hibcheck.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {

    public UserAccount(User user, BigDecimal balance, Currency currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }

    public UserAccount(UserAccountDto accountDto) {
        this.accountId =accountDto.getAccountId();
        this.user = accountDto.getUser().toUserAccount();
        this.balance = accountDto.getBalance();
        this.currency = accountDto.getCurrency();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    Currency currency;
}
