package com.study.spring.user.repository;

import com.study.spring.config.ConnectionMaker;
import javax.sql.DataSource;

public class DUserRepository extends UserRepository{

    public DUserRepository() {
    }

    public DUserRepository(DataSource dataSource) {
        super(dataSource);
    }
}
