package com.example.spring.service;

import com.example.spring.dto.RegistrationDto;
import com.example.spring.entity.UserEntity;
import com.example.spring.entity.UserStatus;
import com.example.spring.repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepositoryJpa userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepositoryJpa userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean registerUser(RegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);
        return true;
    }
}