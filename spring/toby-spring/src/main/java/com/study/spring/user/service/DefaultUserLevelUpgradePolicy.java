package com.study.spring.user.service;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;

public class DefaultUserLevelUpgradePolicy implements UserLevelUpgradePolicy {

    public static final int MIN_LOG_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;


    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOG_COUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        }
    }

    @Override
    public User upgradeLevel(User user) {
        Level nextLevel = user.getLevel().nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(user.getLevel() + "은 업그레이드 불가");
        } else {
            user.setLevel(nextLevel);
        }
        return user;
    }
}
