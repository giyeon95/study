package com.study.spring.user.repository;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRespsitoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;


    @BeforeEach
    void beforeEach() {
        this.user1 = User.builder().id("kiyeon")
            .name("김기연")
            .password("1234")
            .level(Level.BASIC)
            .login(1)
            .recommend(0)
            .build();

        this.user2 = User.builder().id("leegw700")
            .name("이길원")
            .password("1234")
            .level(Level.SILVER)
            .login(55)
            .recommend(10)
            .build();

        this.user3 = User.builder().id("bumjin")
            .name("박범진")
            .password("1234")
            .level(Level.GOLD)
            .login(100)
            .recommend(40)
            .build();
    }




}
