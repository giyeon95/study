package com.study.spring.user.repository;

import com.study.spring.config.ConnectionMaker;
import com.study.spring.user.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;

public abstract class UserRepository {

    //    @Setter
//    private ConnectionMaker connectionMaker;
    @Setter
    private DataSource dataSource;

    public UserRepository() {

    }

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password)"
            + "values(?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();
        User user = null;
        if (rs.next()) {
            user = User.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .build();

        }
        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.executeUpdate();
        ps.close();
        c.close();
    }

}
