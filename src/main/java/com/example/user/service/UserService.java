package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.entity.UserTO;

import java.util.List;

public interface UserService {


    User save(UserTO user);
    List<User> findAll();
    void delete(long id);

    User findOne(String username);

    User findById(long id);

    UserTO update(UserTO userDto);

}
