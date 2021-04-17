package com.study.spring.user.service;

import com.study.spring.user.domain.User;
import java.sql.SQLException;

public interface UserService {

    void upgradeLevels() throws SQLException;

    void upgradeLevel(User user);


    void add(User user);
}
