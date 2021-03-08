package com.study.spring.user.repository;

import javax.sql.DataSource;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryFactory {

    private final DataSource dataSource;

    @Autowired
    public RepositoryFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserRepository nUserRepository() {
        return new NUserRepository(dataSource);
    }

    @Bean
    public UserRepository dUserRepository() {
        return new DUserRepository(dataSource);
    }

}
