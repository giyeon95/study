package com.study.spring.user;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.AppConfig;
import com.study.spring.user.service.DefaultUserLevelUpgradePolicy;
import com.study.spring.user.service.UserLevelUpgradePolicy;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {

    User user;
    UserLevelUpgradePolicy userLevelUpgradePolicy;

    @BeforeEach
    void beforeEach() {
        user = new User();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        this.userLevelUpgradePolicy = ac
            .getBean("userLevelUpgradePolicy", UserLevelUpgradePolicy.class);
    }

    @Test
    @DisplayName("최고레벨이 아닌경우, 다음 레벨로 변경된다.")
    void upgradeLevel() {
        Level[] levels = Level.values();

        Arrays.stream(levels)
            .filter(level -> nonNull(level.nextLevel()))
            .forEach(level -> {
                user.setLevel(level);
                userLevelUpgradePolicy.upgradeLevel(user);
                assertThat(user.getLevel()).isEqualTo(level.nextLevel());
            });
    }

    @Test
    @DisplayName("최고 레벨에서 업그레이드시 예외가 발생한다.")
    void cannotUpgradeLevel() {
        Level[] levels = Level.values();
        Arrays.stream(levels)
            .filter(level -> isNull(level.nextLevel()))
            .forEach(level -> {
                user.setLevel(level);
                assertThrows(IllegalStateException.class,
                    () -> userLevelUpgradePolicy.upgradeLevel(user));
            });
    }

}
