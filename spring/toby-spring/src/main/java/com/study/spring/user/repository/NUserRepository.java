package com.study.spring.user.repository;

import javax.sql.DataSource;

public class NUserRepository extends UserRepository {


    public NUserRepository(DataSource dataSource, JdbcContext jdbcContext) {
        super(dataSource, jdbcContext);
    }
}
