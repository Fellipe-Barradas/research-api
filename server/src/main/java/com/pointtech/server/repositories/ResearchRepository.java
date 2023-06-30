package com.pointtech.server.repositories;
import com.pointtech.server.entities.Research;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ResearchRepository extends JpaRepository<Research, Long>{
    
}
