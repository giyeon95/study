package com.study.spring.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.study.spring.user.domain.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    void userRepositoryCountingTest() throws SQLException {

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

    @Test
    void getUserAllEmptyTest() throws SQLException {
        repository.deleteAll();

        List<User> users = repository.getAll();

        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    void getUserAllTest() throws SQLException {
        repository.deleteAll();

        User user1 = User.builder()
            .id("user1")
            .name("u1")
            .password("p1")
            .build();

        repository.add(user1);

        List<User> users1 = repository.getAll();

        assertThat(users1.size()).isEqualTo(1);
        checkSameUser(user1, users1.get(0));

        User user2 = User.builder()
            .id("kiyeon95")
            .name("k2")
            .password("p2")
            .build();
        repository.add(user2);

        List<User> users2 = repository.getAll();
        assertThat(users2.size()).isEqualTo(2);
        checkSameUser(user2, users2.get(0)); // id abc 정렬
        checkSameUser(user1, users2.get(1));

        User user3 = User.builder()
            .id("abc1")
            .name("u3")
            .password("p3")
            .build();
        repository.add(user3);
        List<User> users3 = repository.getAll();
        assertThat(users3.size()).isEqualTo(3);

        checkSameUser(user3, users3.get(0));
        checkSameUser(user2, users3.get(1));
        checkSameUser(user1, users3.get(2));
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());

    }
}