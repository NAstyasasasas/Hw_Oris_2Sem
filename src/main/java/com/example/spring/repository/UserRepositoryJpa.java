package com.example.spring.repository;

import com.example.spring.entity.UserEntity;
import com.example.spring.entity.UserStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class UserRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    public Optional<UserEntity> findById(UUID id) {
        UserEntity user = em.find(UserEntity.class, id);
        return Optional.ofNullable(user);
    }

    public List<UserEntity> findAll() {
        return em.createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

    public List<UserEntity> findByStatus(UserStatus status) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE u.status = :status", UserEntity.class)
                .setParameter("status", status)
                .getResultList();
    }

    public UserEntity update(UserEntity user) {
        return em.merge(user);
    }

    public void deleteById(UUID id) {
        UserEntity user = em.find(UserEntity.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}