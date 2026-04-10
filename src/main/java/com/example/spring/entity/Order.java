package com.example.spring.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")  // внешний ключ
    private UserEntity user;

    public Order() {}

    public Order(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    @Override
    public String toString() {
        return "Order{id=" + id + ", description='" + description + "'}";
    }
}