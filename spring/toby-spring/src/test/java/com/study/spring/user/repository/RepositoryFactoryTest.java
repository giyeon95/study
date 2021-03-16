package com.study.spring.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.study.spring.user.domain.User;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
class RepositoryFactoryTest {

    private UserRepository repository;

    ApplicationContext context;

    @BeforeEach
    void beforeEach() {
        this.context = new AnnotationConfigApplicationContext(CountingRepositoryFactory.class);
        this.repository = context.getBean("userRepository", UserRepository.class);
    }


    @Test
    void userRepositoryCountingTest() throws SQLException, ClassNotFoundException {

        repository.deleteAll();
        repository.add(User.builder()
            .id("kiyeon")
            .name("TESTUSER")
            .password("pw")
            .build()
        );

        assertEquals(1, repository.getCount()); // getCount TEST

        repository.add(User.builder()
            .id("kiyeon2")
            .name("TEST2")
            .password("pw3")
            .build());

        assertEquals(2, repository.getCount()); // getCount2

        repository.deleteAll();
        int countAfterDelete = repository.getCount();
        assertEquals(0, countAfterDelete);
    }


    @Test
    void getUserFailTest() throws SQLException {

        repository.deleteAll();
        assertEquals(0, repository.getCount());

        assertThrows(EmptyResultDataAccessException.class, () -> repository.get("unknown"));

    }
}