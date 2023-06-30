package com.pointtech.server.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.Research;
import com.pointtech.server.repositories.ResearchRepository;
import com.pointtech.server.services.exceptions.DatabaseException;
import com.pointtech.server.services.exceptions.ElementAlreadyExists;
import com.pointtech.server.services.exceptions.ElementNotFounded;

@Service
public class ResearchServices {

    @Autowired
    ResearchRepository researchRepository;

    public List<Research> findAll() {
        return researchRepository.findAll();
    }

    public Research findById(Long id) {
        try{
            return researchRepository.findById(id).get();
        }catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }
        
    }

    public Research insert(Research research) {
        if(findById(research.getId()) != null){
            throw new ElementAlreadyExists("User already exists!");
        }
        return researchRepository.save(research);
    }

    public void delete(Long id){
       try {
            researchRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    public Research update(Long id, Research research){
         try {
            Research entity = researchRepository.getReferenceById(id);
            updateData(entity, research);
            return researchRepository.save(entity);
        } catch (NoSuchElementException e) {
            throw new ElementNotFounded(id);
        }
    }

    private void updateData(Research entity, Research research) {
        entity.setDescription(research.getDescription());
        entity.setMoment(research.getMoment());
        entity.setTitle(research.getTitle());
    }
}
