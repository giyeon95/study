package com.study.spring.user.repository;

import com.study.spring.config.ConnectionMaker;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;

public class CountingConnectionMaker implements ConnectionMaker {

    @Getter
    private int counter = 0;

    @Setter
    private DataSource dataSource;

    public CountingConnectionMaker() {

    }


    public CountingConnectionMaker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return dataSource.getConnection();
    }


}
