package com.goopnigoop.hibcheck.repositories;

import com.goopnigoop.hibcheck.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class UserRepositoryTCIntegrationTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @BeforeEach
    void clear() {
        userAccountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("should save users")
    void testSaveAllUsers() {
        insertUsers();
        final List<User> all = userRepository.findAll();
        assertThat(all.size(), is(4));
    }

    @Test
    @Transactional
    @DisplayName("should reject the duplicate user")
    void testRejectDuplicateUSer() {
        insertUsers();
        final User user = new User("SAMPLE", "0", LocalDate.of(2001, 12, 12));
        assertThrows(DataAccessException.class, () -> userRepository.save(user));
    }

    private void insertUsers() {
        userRepository.save(new User("SAMPLE", "0", LocalDate.of(2001, 12, 12)));
        userRepository.save(new User("SAMPLE1", "1", LocalDate.now()));
        userRepository.save(new User("SAMPLE", "2", LocalDate.now()));
        userRepository.save(new User("SAMPLE3", "3,", LocalDate.now()));
    }


}
