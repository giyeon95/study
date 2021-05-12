package com.study.spring.user.service;

import static com.study.spring.user.service.DefaultUserLevelUpgradePolicy.MIN_LOG_COUNT_FOR_SILVER;
import static com.study.spring.user.service.DefaultUserLevelUpgradePolicy.MIN_RECOMMEND_FOR_GOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.study.spring.email.EmailDTO;
import com.study.spring.email.EmailUtils;
import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.AppConfig;
import com.study.spring.user.repository.UserRepository;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootTest(classes = AppConfig.class)
class UserServiceImplTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserLevelUpgradePolicy userLevelUpgradePolicy;
    private final PlatformTransactionManager transactionManager;
    private final UserServiceImpl userServiceImpl;

    List<User> users;

    @Autowired
    public UserServiceImplTest(UserService userService,
        UserRepository userRepository,
        UserLevelUpgradePolicy userLevelUpgradePolicy,
        PlatformTransactionManager transactionManager,
        UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
        this.transactionManager = transactionManager;
        this.userServiceImpl = userServiceImpl;
    }

    @BeforeEach
    void setUp() {
        users = Arrays.asList(
            new User("abumjin", "박범진", "p1", "giyeon15@gmail.com", Level.BASIC,
                MIN_LOG_COUNT_FOR_SILVER - 1, 0),
            new User("bjoytouch", "강명성", "p2", "giyeon15@gmail.com", Level.BASIC,
                MIN_LOG_COUNT_FOR_SILVER, 0),
            new User("cerwins", "신승한", "p3", "giyeon15@gmail.com", Level.SILVER, 60,
                MIN_RECOMMEND_FOR_GOLD - 1),
            new User("dmadnite1", "이상호", "p4", "giyeon15@gmail.com", Level.SILVER, 60,
                MIN_RECOMMEND_FOR_GOLD),
            new User("egreen", "오민규", "p5", "giyeon15@gmail.com", Level.GOLD, 100,
                Integer.MAX_VALUE)
        );

    }

    @Test
    @DirtiesContext
    void upgradeLevelsTest() throws SQLException {
        userRepository.deleteAll();
        users.forEach(userRepository::add);

        MockMailSender mockMailSender = new MockMailSender();

        userServiceImpl.setEmailUtils(mockMailSender);

        this.userService.upgradeLevels();
        checkLevelUpgrade(users.get(0), false);
        checkLevelUpgrade(users.get(1), true);
        checkLevelUpgrade(users.get(2), false);
        checkLevelUpgrade(users.get(3), true);
        checkLevelUpgrade(users.get(4), false);

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size()).isEqualTo(2);
        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());

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

        TestUserService testUserService = new TestUserService(
            userRepository,
            userLevelUpgradePolicy,
            emailDTO -> System.out.println("Eamil Send  = " + emailDTO));

//        UserServiceTx userServiceTx = new UserServiceTx(testUserService, transactionManager);
        UserService proxyUserService = (UserService) Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[]{UserService.class},
            new TransactionHandler(testUserService, transactionManager, "upgradeLevels")
        );

        testUserService.setId("dmadnite1");
        userRepository.deleteAll();
        users.forEach(userRepository::add);

        try {
            proxyUserService.upgradeLevels();
            fail("TestUserServcice Exception expected");
        } catch (TestUserServiceException | SQLException e) {

        }

        checkLevelUpgrade(users.get(1), false);
    }

    @Test

    public void upgradeLevels() throws Exception {

//        MockUserRepository mockUserRepository = new MockUserRepository(users);
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.getAll()).thenReturn(this.users);
//        MockMailSender mockMailSender = new MockMailSender();
        EmailUtils mockMailSender = mock(EmailUtils.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(mockUserRepository,
            userLevelUpgradePolicy, mockMailSender);

        userServiceImpl.upgradeLevels();

        verify(mockUserRepository, times(2)).update(any(User.class));
        verify(mockUserRepository, times(2)).update(any(User.class));
        verify(mockUserRepository).update(users.get(1));

        assertThat(users.get(1).getLevel()).isEqualTo(Level.SILVER);
        verify(mockUserRepository).update(users.get(3));
        assertThat(users.get(3).getLevel()).isEqualTo(Level.GOLD);

        ArgumentCaptor<EmailDTO> emailDtoArg = ArgumentCaptor.forClass(EmailDTO.class);

        verify(mockMailSender, times(2)).send(emailDtoArg.capture());

        List<EmailDTO> mailMessages = emailDtoArg.getAllValues();
        assertThat(mailMessages.get(0).getReceiver()).isEqualTo(users.get(1).getEmail());
        assertThat(mailMessages.get(1).getReceiver()).isEqualTo(users.get(3).getEmail());

//        List<User> updated = mockUserRepository.getUpdated();
//        assertThat(updated.size()).isEqualTo(2);

//        checkUserAndLevel(updated.get(0), "bjoytouch", Level.SILVER);
//        checkUserAndLevel(updated.get(1), "dmadnite1", Level.GOLD);
//
//
//        List<String> request = mockMailSender.getRequests();
//        assertThat(request.size()).isEqualTo(2);
//        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
//        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());
    }

    private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
        assertThat(updated.getId()).isEqualTo(expectedId);
        assertThat(updated.getLevel()).isEqualTo(expectedLevel);
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

        public TestUserService(UserRepository userRepository,
            UserLevelUpgradePolicy userLevelUpgradePolicy, EmailUtils emailUtils) {
            super(userRepository, userLevelUpgradePolicy, emailUtils);
        }


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


    }

    static class MockMailSender implements EmailUtils {

        private List<String> requests = new ArrayList<>();

        public List<String> getRequests() {
            return requests;
        }

        @Override
        public void send(EmailDTO emailDTO) {
            requests.add(emailDTO.getReceiver());
        }
    }

    static class MockUserRepository implements UserRepository {

        private final List<User> users;

        private final List<User> updated = new ArrayList<>();

        public MockUserRepository(List<User> users) {
            this.users = users;
        }

        public List<User> getUpdated() {
            return this.updated;
        }

        @Override
        public void add(User user) {
            throw new UnsupportedOperationException();
        }

        @Override
        public User get(String id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<User> getAll() {
            return this.users;
        }

        @Override
        public void deleteAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getCount() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void update(User user) {
            this.updated.add(user);
        }
    }
}