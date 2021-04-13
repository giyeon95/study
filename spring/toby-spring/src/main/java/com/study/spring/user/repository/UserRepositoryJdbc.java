package com.study.spring.user.repository;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import java.util.List;
import javax.sql.DataSource;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRepositoryJdbc implements UserRepository {

    @Setter
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> rowMapper =
        (rs, rowNum) -> User.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .level(Level.valueOf(rs.getInt("level")))
            .login(rs.getInt("login"))
            .recommend(rs.getInt("recommend"))
            .build();

    public UserRepositoryJdbc() {

    }

    public UserRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(
            "insert into users(id, name, password, level, login, recommend) values (?, ?, ?, ?, ?, ?)",
            user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(),
            user.getLogin(), user.getRecommend());
    }

    @Override
    public User get(String id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", rowMapper, id);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id", rowMapper);

    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?,"
                + "recommend = ? where id = ?",
            user.getName(), user.getPassword(), user.getLevel().intValue(),
            user.getLogin(), user.getRecommend(), user.getId());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }
}
