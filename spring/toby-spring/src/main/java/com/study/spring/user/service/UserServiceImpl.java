package com.study.spring.user.service;

import com.study.spring.email.EmailDTO;
import com.study.spring.email.EmailUtils;
import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLevelUpgradePolicy userLevelUpgradePolicy;
    private EmailUtils emailUtils;

    public UserServiceImpl(UserRepository userRepository,
        UserLevelUpgradePolicy userLevelUpgradePolicy,
        EmailUtils emailUtils) {
        this.userRepository = userRepository;
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
        this.emailUtils = emailUtils;
    }

    public void setEmailUtils(EmailUtils emailUtils) {
        this.emailUtils = emailUtils;
    }

    @Override
    public void upgradeLevels() throws SQLException {
        List<User> users = userRepository.getAll();
        users.forEach(this::upgradeLevel);
    }


    @Override
    public void upgradeLevel(User user) {
        if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
            User upgradeUser = userLevelUpgradePolicy.upgradeLevel(user);
            userRepository.update(upgradeUser);

            emailUtils.send(
                EmailDTO.builder()
                    .receiver(user.getEmail())
                    .subject("Upgrade 안내")
                    .contents("사용자님의 등급이" + user.getLevel().name() + "로 업그레이드 되었습니다.")
                    .build());
        }
    }


    @Override
    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userRepository.add(user);
    }

}
