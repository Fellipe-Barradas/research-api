package com.pointtech.server.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointtech.server.entities.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @GetMapping
    public ResponseEntity<User> findUser() {
        User obj = new User(1L,"luis","luis@gmail","3231");
        return ResponseEntity.ok().body(obj);
    }

    
    
}
