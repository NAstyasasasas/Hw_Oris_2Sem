package com.example.spring.repository;

import com.example.spring.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryTemplate implements CrudRepository<UserEntity, UUID> {

    private final NamedParameterJdbcTemplate jdbc;

    public UserRepositoryTemplate(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(UserEntity user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }

        String sql = "INSERT INTO users (id, name) VALUES (:id, :name)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName());

        jdbc.update(sql, params);
    }

    @Override
    public Optional<UserEntity> getById(UUID id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<UserEntity> users = jdbc.query(sql, params, userMapper);

        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, userMapper);
    }

    @Override
    public void update(UserEntity user) {
        String sql = "UPDATE users SET name = :name WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName());

        jdbc.update(sql, params);
    }

    @Override
    public boolean deleteById(UUID id) {
        String sql = "DELETE FROM users WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        int rowsAffected = jdbc.update(sql, params);
        return rowsAffected > 0;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM users";
        jdbc.update(sql, new MapSqlParameterSource());
    }

    public Optional<UserEntity> getByName(String name) {
        String sql = "SELECT * FROM users WHERE name = :name";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);

        List<UserEntity> users = jdbc.query(sql, params, userMapper);

        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    private final RowMapper<UserEntity> userMapper = (rs, rowNum) -> {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setName(rs.getString("name"));
        return user;
    };
}