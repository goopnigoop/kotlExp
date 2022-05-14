package com.goopnigoop.hibcheck.repositories;

import com.goopnigoop.hibcheck.entities.UserAccount;
import com.goopnigoop.hibcheck.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query(value = "SELECT user FROM UserAccount user WHERE user.user.id = :id")
    UserAccount findByUserId(Integer id);

    @Lock(value = PESSIMISTIC_WRITE)
    @Query(value = "SELECT user_account FROM UserAccount user_account  WHERE user_account.accountId = :accountId and user_account.currency = :currency")
    UserAccount selectForUpdateForAccountIdAndCurrency(Integer accountId, Currency currency);

    @Query(value = "SELECT * FROM user_accounts WHERE account_Id = ?1 and currency = :#{#currency.name()} for update",
    nativeQuery = true)
    UserAccount selectForUpdateForAccountIdAndCurrencyNative(Integer accountId, Currency currency);
}
