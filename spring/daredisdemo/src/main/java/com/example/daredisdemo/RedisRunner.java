package com.example.daredisdemo;

import com.example.daredisdemo.account.Account;
import com.example.daredisdemo.account.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("kiyeon", "kim");
        values.set("spring_boot", "2.0");
        values.set("hello", "world");

        Account account = new Account();
        account.setEmail("kiyeon_kim1@test.com");
        account.setUsername("kiyeon");
        accountRepository.save(account);

        Account byId = accountRepository.findById(account.getId()).orElseThrow();
        System.out.println(byId.getUsername());
        System.out.println(byId.getEmail());
    }
}
