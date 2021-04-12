package com.study.spring.user.repository;

import com.study.spring.config.ConnectionMaker;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryJdbc(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource datasource = new SimpleDriverDataSource();

        datasource.setDriverClass(org.mariadb.jdbc.Driver.class);
        datasource.setUrl("jdbc:mariadb://localhost/test");
        datasource.setUsername("root");
        datasource.setPassword("1234");

        return datasource;
    }

}
