package com.study.spring.user.repository;

import com.study.spring.user.domain.User;
import java.util.List;

public interface UserRepository {

    void add(User user);

    User get(String id);

    List<User> getAll();

    void deleteAll();

    int getCount();

}
