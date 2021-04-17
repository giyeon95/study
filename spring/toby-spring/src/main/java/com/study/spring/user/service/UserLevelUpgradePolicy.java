package com.study.spring.user.service;

import com.study.spring.user.domain.User;

public interface UserLevelUpgradePolicy {

    boolean canUpgradeLevel(User user);

    User upgradeLevel(User user);
}
