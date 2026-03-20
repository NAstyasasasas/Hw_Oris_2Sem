package com.example.spring.service;

import com.example.spring.entity.UserEntity;
import com.example.spring.entity.UserStatus;
import com.example.spring.repository.UserRepositoryTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepositoryTemplate userRepository;

    @Autowired
    public UserService(UserRepositoryTemplate userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(String name) {
        UserEntity user = new UserEntity(name, UserStatus.PENDING);
        userRepository.save(user);
        return user;
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.getById(id);
    }

    public Optional<UserEntity> getUserByName(String name) {
        return userRepository.getByName(name);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean updateUser(UUID id, String newName) {
        Optional<UserEntity> optionalUser = userRepository.getById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setName(newName);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    public boolean deleteUserById(UUID id) {
        return userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public UserEntity getUserByIdOrThrow(UUID id) {
        return userRepository.getById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + id + " не найден"));
    }
}