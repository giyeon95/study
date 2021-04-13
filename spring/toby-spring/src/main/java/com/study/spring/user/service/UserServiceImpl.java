package com.study.spring.user.service;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.UserRepository;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void upgradeLevels() {
        List<User> users = userRepository.getAll();
        users.forEach(user -> {
            boolean changed = false;

            if (user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
                user.setLevel(Level.SILVER);
                changed = true;
            } else if (user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
                user.setLevel(Level.GOLD);
                changed = true;
            } else if (user.getLevel() == Level.GOLD) {
                changed = false;
            }

            if (changed) {
                userRepository.update(user);
            }
        });

    }
}
