package com.pointtech.server.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointtech.server.entities.User;
import com.pointtech.server.services.UserServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserServices userServices;

    @GetMapping
    public ResponseEntity<List<User>> findUser() {
        List<User> list = userServices.findAll();
        return ResponseEntity.ok().body(list);

    }

    
    
}
