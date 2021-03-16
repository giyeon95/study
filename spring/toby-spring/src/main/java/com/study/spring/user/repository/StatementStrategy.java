package com.study.spring.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection c) throws SQLException;

}
