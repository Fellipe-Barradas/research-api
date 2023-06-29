package com.pointtech.server.repositories;
import com.pointtech.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long>{
    
}
