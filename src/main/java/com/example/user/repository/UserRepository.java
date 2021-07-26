package com.example.user.repository;


import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginAndPassword(String login, String password);
    public User findByLogin(String login);
    public List<User> findByAdmin(Boolean admin);
}