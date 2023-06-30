package com.pointtech.server.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.Rating;
import com.pointtech.server.repositories.RatingRepository;
import com.pointtech.server.services.exceptions.DatabaseException;
import com.pointtech.server.services.exceptions.ElementAlreadyExists;
import com.pointtech.server.services.exceptions.ElementNotFounded;

@Service
public class RatingServices {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating findById(Long id) {
        try {
            return ratingRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }  
    }

    public Rating insert(Rating rating) {
         if(findById(rating.getId()) != null){
            throw new ElementAlreadyExists("User already exists!");
        }
        return ratingRepository.save(rating);
    }

    public void delete(Long id){
        try {
            ratingRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    public Rating update(Long id, Rating rating){
       try {
            Rating entity = ratingRepository.getReferenceById(id);
            updateData(entity, rating);
            return ratingRepository.save(entity);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }
    }

    private void updateData(Rating entity, Rating rating) {
        entity.setScore(rating.getScore());
        entity.setFeedback(rating.getFeedback());
    }
}
