package com.example.spring.service;

import com.example.spring.entity.Order;
import com.example.spring.entity.UserEntity;
import com.example.spring.entity.UserStatus;
import com.example.spring.repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceJpa {

    private final UserRepositoryJpa userRepository;

    @Autowired
    public UserServiceJpa(UserRepositoryJpa userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(String name, UserStatus status) {
        UserEntity user = new UserEntity(name, status);
        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserEntity> getUsersByStatus(UserStatus status) {
        return userRepository.findByStatus(status);
    }

    public UserEntity updateStatus(UUID id, UserStatus newStatus) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setStatus(newStatus);
            return userRepository.update(user);
        }
        return null;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public boolean addOrderToUser(UUID userId, String orderDescription) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            Order order = new Order(orderDescription);
            user.addOrder(order);
            userRepository.update(user);
            return true;
        }
        return false;
    }
}