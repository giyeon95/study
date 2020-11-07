package com.example.springdatademo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        Account account = new Account();
        account.setEmail("kiyeon@test.com");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull();

        Optional<Account> existAccount = accountRepository.findByEmail(newAccount.getEmail());

        assertThat(existAccount).isNotEmpty();

        Optional<Account> notExistAccount = accountRepository.findByEmail("test_name@test.com");
        assertThat(notExistAccount).isEmpty();

    }
}