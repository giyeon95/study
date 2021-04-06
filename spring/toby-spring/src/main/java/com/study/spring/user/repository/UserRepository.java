package com.study.spring.user.repository;

import com.study.spring.user.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public abstract class UserRepository {

    @Setter
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> rowMapper =
        (rs, rowNum) -> User.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .build();

    public UserRepository() {

    }

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) throws SQLException {
        jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)",
            user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) throws SQLException {
        return jdbcTemplate.queryForObject("select * from users where id = ?", rowMapper);
    }

    public List<User> getAll() throws SQLException {
        return jdbcTemplate.query("select * from users order by id", rowMapper);

    }

    public int getCount() throws SQLException {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public void deleteAll() throws SQLException {
        jdbcTemplate.update("delete from users");
    }
}
