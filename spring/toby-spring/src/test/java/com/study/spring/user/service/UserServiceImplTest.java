package com.study.spring.user.service;

import static com.study.spring.user.service.DefaultUserLevelUpgradePolicy.MIN_LOG_COUNT_FOR_SILVER;
import static com.study.spring.user.service.DefaultUserLevelUpgradePolicy.MIN_RECOMMEND_FOR_GOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.AppConfig;
import com.study.spring.user.repository.UserRepository;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = AppConfig.class)
class UserServiceImplTest {

    private final  UserService userService;
    private final UserRepository userRepository;
    private final UserLevelUpgradePolicy userLevelUpgradePolicy;
    private final DataSource dataSource;

    List<User> users;

    @Autowired
    public UserServiceImplTest(UserService userService,
        UserRepository userRepository,
        UserLevelUpgradePolicy userLevelUpgradePolicy, DataSource dataSource) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
        this.dataSource = dataSource;
    }

    @BeforeEach
    void setUp() {
        users = Arrays.asList(
            new User("abumjin", "박범진", "p1", Level.BASIC, MIN_LOG_COUNT_FOR_SILVER - 1, 0),
            new User("bjoytouch", "강명성", "p2", Level.BASIC, MIN_LOG_COUNT_FOR_SILVER, 0),
            new User("cerwins", "신승한", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1),
            new User("dmadnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
            new User("egreen", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
        );
    }

    @Test
    void upgradeLevelsTest() throws SQLException {
        userRepository.deleteAll();
        users.forEach(userRepository::add);

        userService.upgradeLevels();
        checkLevelUpgrade(users.get(0), false);
        checkLevelUpgrade(users.get(1), true);
        checkLevelUpgrade(users.get(2), false);
        checkLevelUpgrade(users.get(3), true);
        checkLevelUpgrade(users.get(4), false);

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

    @Test
    @DisplayName("강제 예외 발생을 통한 테스트")
    void upgradeAllOrNoting() {

        TestUserService testUserService = new TestUserService(userRepository, userLevelUpgradePolicy, dataSource);
        testUserService.setId("dmadnite1");
        userRepository.deleteAll();
        users.forEach(userRepository::add);

        try {
            testUserService.upgradeLevels();
            fail("TestUserServcice Exception expected");
        } catch (TestUserServiceException | SQLException e) {

        }

        checkLevelUpgrade(users.get(1), false);
    }


    private void checkLevelUpgrade(User user, boolean upgraded) {
        User userUpdate = userRepository.get(user.getId());
        Level level = userUpdate.getLevel();

        if (upgraded) {
            assertThat(level).isEqualTo(user.getLevel().nextLevel());
        } else {
            assertThat(level).isEqualTo(user.getLevel());
        }
    }

    static class TestUserServiceException extends RuntimeException {

    }

    static class TestUserService extends UserServiceImpl {

        private String id;

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) {
                throw new TestUserServiceException();
            }

            super.upgradeLevel(user);
        }

        public TestUserService(UserRepository userRepository,
            UserLevelUpgradePolicy userLevelUpgradePolicy, DataSource dataSource) {
            super(userRepository, userLevelUpgradePolicy, dataSource);
        }
    }
}