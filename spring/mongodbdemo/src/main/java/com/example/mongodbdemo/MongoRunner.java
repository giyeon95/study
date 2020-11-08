package com.example.mongodbdemo;

import com.example.mongodbdemo.account.Account;
import com.example.mongodbdemo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setEmail("giyeon15@gmail.com");
        account.setUsername("kiyeon_kim1");
        accountRepository.save(account);

        System.out.println("finished");
    }
}
