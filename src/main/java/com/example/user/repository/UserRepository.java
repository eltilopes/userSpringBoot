package com.example.user.repository;


import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginAndPassword(String login, String password);
    User findByLogin(String login);
}