package com.study.spring.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.AppConfig;
import com.study.spring.user.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = AppConfig.class)
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    List<User> users;

    @BeforeEach
    void setUp() {
        users = Arrays.asList(
            new User("abumjin", "박범진", "p1", Level.BASIC, 49, 0),
            new User("bjoytouch", "강명성", "p2", Level.BASIC, 50, 0),
            new User("cerwins", "신승한", "p3", Level.SILVER, 60, 29),
            new User("dmadnite1", "이상호", "p4", Level.SILVER, 60, 30),
            new User("egreen", "오민규", "p5", Level.GOLD, 100, 100)
        );
    }

    @Test
    void upgradeLevelTest() {
        userRepository.deleteAll();
        users.forEach(userRepository::add);

        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);

    }

    @Test
    @DisplayName("생성시 Level이 없으면 BASIC 존재시 유지한다.")
    void addLevelTest() {
        userRepository.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userRepository.get(userWithLevel.getId());
        User userWithoutLevelRead = userRepository.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevel.getLevel());
        assertThat(userWithoutLevelRead.getLevel()).isEqualTo(Level.BASIC);

    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userRepository.get(user.getId());
        assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }

}