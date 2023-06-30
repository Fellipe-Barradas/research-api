package com.pointtech.server.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pointtech.server.entities.Research;
import com.pointtech.server.services.ResearchServices;

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
@RequestMapping(value = "/researchies")
public class ResearchResource {

    @Autowired
    ResearchServices researchServices;

    @GetMapping
    public ResponseEntity<List<Research>> findUser() {
        List<Research> list = researchServices.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Research> findById(@PathVariable Long id) {
        Research rating = researchServices.findById(id);
        return ResponseEntity.ok().body(rating);
    }

    @PostMapping
    public ResponseEntity<Research> insert(@RequestBody Research obj ) {
        Research rating = researchServices.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        researchServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Research> update(@PathVariable Long id, @RequestBody Research user) {
        user = researchServices.update(id, user);
        return ResponseEntity.ok().body(user);
    }
        
}
