package repository;

import entity.UserEntity;
import org.springframework.stereotype.Component;
import utils.DataClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRepository {

    private Connection cn;

    public UserRepository() {
        this.cn = DataClass.getConn();
    }

    public void addUser(UserEntity user) {
        String sql = "insert into users(name) values (?)";

        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity getUser(String name) {
        String sql = "select * from users where name = ?";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserEntity user = new UserEntity(rs.getLong("id"), rs.getString("name"));
                return user;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void upUser(Long id, String name) {
        String sql = "update users set name = ? where id = ?";

        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1,name);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUser(UserEntity user) {
        String sql = "delete from users where name = ?";

        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
