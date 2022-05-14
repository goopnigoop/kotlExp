package com.goopnigoop.hibcheck.repositories;

import com.goopnigoop.hibcheck.dto.UserAccountDto;
import com.goopnigoop.hibcheck.dto.UserDto;
import com.goopnigoop.hibcheck.enums.Currency;
import com.goopnigoop.hibcheck.services.TransferService;
import com.goopnigoop.hibcheck.services.UserAccountService;
import com.goopnigoop.hibcheck.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class UserTransferTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    @Qualifier("transferService")
    private TransferService transferService;
    @Autowired
    @Qualifier("nativeTransferService")
    private TransferService nativeTransferService;

    private final static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @BeforeEach
    void clear() {
        userAccountService.clear();
        userService.clear();
    }

    @Test
    @DisplayName("should transfer money from one account to another")
    void testTransfer() {
        final UserDto userFirst = userService.createUser("First", "User", LocalDate.of(2001, 12, 12));
        final UserDto userTwo = userService.createUser("Second", "UserTwo", LocalDate.of(2001, 12, 12));
        final BigDecimal firstInitial = new BigDecimal("100.00");
        final UserAccountDto from = userAccountService.createUserAccountForUser(userFirst, firstInitial, Currency.USD);
        final BigDecimal secondInitial = new BigDecimal("50.00");
        final UserAccountDto to = userAccountService.createUserAccountForUser(userTwo, secondInitial, Currency.USD);
        final BigDecimal delta = new BigDecimal("5");
        transferService.transfer(from, to, delta);
        final UserAccountDto userAccountFrom = userAccountService.getUserAccountForUser(userFirst);
        final UserAccountDto userAccountTo = userAccountService.getUserAccountForUser(userTwo);
        assertThat(userAccountFrom.getBalance(), is(firstInitial.subtract(delta)));
        assertThat(userAccountTo.getBalance(), is(secondInitial.add(delta)));
    }


    @Test
    @DisplayName("should transfer money from one account to another in 2 threads using native query")
    void testTransferInThreadsForNative() {
        processTransferFor(nativeTransferService);
    }

    @Test
    @DisplayName("should transfer money from one account to another in 2 threads")
    void testTransferInThreads() {
        processTransferFor(transferService);
    }

    private void processTransferFor(TransferService currentTransferService) {
        final UserDto userFirst = userService.createUser("First", "User", LocalDate.of(2001, 12, 12));
        final UserDto userTwo = userService.createUser("Second", "UserTwo", LocalDate.of(2001, 12, 12));
        final BigDecimal firstInitial = new BigDecimal("100.00");
        final UserAccountDto from = userAccountService.createUserAccountForUser(userFirst, firstInitial, Currency.USD);
        final BigDecimal secondInitial = new BigDecimal("50.00");
        final UserAccountDto to = userAccountService.createUserAccountForUser(userTwo, secondInitial, Currency.USD);
        final BigDecimal delta = new BigDecimal("5");
        final CountDownLatch countDownLatch = new CountDownLatch(2000);
        IntStream.range(0, 1000).forEach(i -> {
            executorService.submit(() -> {
                currentTransferService.transfer(from, to, delta);
                countDownLatch.countDown();
            });
            executorService.submit(() -> {
                currentTransferService.transfer(to, from, delta);
                countDownLatch.countDown();
            });
        });

        try {
            countDownLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException("The executor service is not finished properly");
        }


        final UserAccountDto userAccountFrom = userAccountService.getUserAccountForUser(userFirst);
        final UserAccountDto userAccountTo = userAccountService.getUserAccountForUser(userTwo);
        assertThat(userAccountFrom.getBalance(), is(firstInitial));
        assertThat(userAccountTo.getBalance(), is(secondInitial));
    }

}