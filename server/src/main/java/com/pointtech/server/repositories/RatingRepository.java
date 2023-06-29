package com.pointtech.server.repositories;
import com.pointtech.server.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    
}
