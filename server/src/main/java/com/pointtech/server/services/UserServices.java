package com.pointtech.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.User;
import com.pointtech.server.repositories.UserRepository;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
