package com.study.spring.user;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    User user;

    @BeforeEach
    void beforeEach() {
        user = new User();
    }

    @Test
    @DisplayName("레벨업이 잘 업데이트 되야한다.")
    void upgradeLevel() {
        Level[] levels = Level.values();

        Arrays.stream(levels)
            .filter(level -> nonNull(level.nextLevel()))
            .forEach(level -> {
                user.setLevel(level);
                user.upgradeLevel();
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
                assertThrows(IllegalStateException.class, () -> user.upgradeLevel());
            });
    }

}
