package com.pointtech.server.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pointtech.server.entities.Rating;
import com.pointtech.server.services.RatingServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/ratings")
public class RatingResource {

    @Autowired
    RatingServices ratingServices;

    @GetMapping
    public ResponseEntity<List<Rating>> findUser() {
        List<Rating> list = ratingServices.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rating> findById(@PathVariable Long id) {
        Rating rating = ratingServices.findById(id);
        return ResponseEntity.ok().body(rating);
    }

    @PostMapping
    public ResponseEntity<Rating> insert(@RequestBody Rating obj ) {
        Rating rating = ratingServices.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ratingServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Rating> update(@PathVariable Long id, @RequestBody Rating user) {
        user = ratingServices.update(id, user);
        return ResponseEntity.ok().body(user);
    }
    
    
}
