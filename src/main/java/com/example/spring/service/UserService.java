package com.example.spring.service;


import com.example.spring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring.repository.UserRepository;

@Service
public class UserService {
    private UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public void createUser(UserEntity user) {
        ur.addUser(user);
    }

    public void delUser(UserEntity user) {
        ur.deleteUser(user);
    }
    public void updateUser(Long id, String name) {
        ur.upUser(id,name);
    }
    public UserEntity getUserr(String name) {
        return ur.getUser(name);
    }
}
