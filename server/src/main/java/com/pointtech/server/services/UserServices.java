package com.pointtech.server.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.User;
import com.pointtech.server.repositories.UserRepository;
import com.pointtech.server.services.exceptions.DatabaseException;
import com.pointtech.server.services.exceptions.ElementAlreadyExists;
import com.pointtech.server.services.exceptions.ElementNotFounded;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }  
    }

    public User insert(User user) {
        if(findById(user.getId()) != null){
            throw new ElementAlreadyExists("User already exists!");
        }
        return userRepository.save(user);
    }

    public void delete(Long id){
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
        
    }

    public User update(Long id, User user){
        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, user);
            return userRepository.save(entity);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
    }
}
