/*
package com.example.spring.repository;


import com.example.spring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.spring.utils.DataClass;
import java.sql.*;

@Repository
public class UserRepository {

    private final DataClass dataClass;

    @Autowired
    public UserRepository(DataClass dataClass) {
        this.dataClass = dataClass;
    }

    public void addUser(UserEntity user) {
        String sql = "insert into users(name) values (?)";
        try (Connection cn = dataClass.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity getUser(String name) {
        String sql = "select * from users where name = ?";
        try (Connection cn = dataClass.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserEntity(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void upUser(Long id, String name) {
        String sql = "update users set name = ? where id = ?";
        try (Connection cn = dataClass.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(UserEntity user) {
        String sql = "delete from users where name = ?";
        try (Connection cn = dataClass.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

 */