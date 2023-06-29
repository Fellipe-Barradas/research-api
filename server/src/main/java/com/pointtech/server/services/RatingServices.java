package com.pointtech.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.Rating;
import com.pointtech.server.repositories.RatingRepository;

@Service
public class RatingServices {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating findById(Long id) {
        return ratingRepository.findById(id).get();
    }

    public Rating insert(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    public void delete(Long id){
        ratingRepository.deleteById(id);
    }

    public Rating update(Long id, Rating rating){
        Rating entity = ratingRepository.getReferenceById(id);
        updateData(entity, rating);
        return ratingRepository.save(entity);
    }

    private void updateData(Rating entity, Rating rating) {
        entity.setScore(rating.getScore());
        entity.setFeedback(rating.getFeedback());
    }
}
