package com.pointtech.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointtech.server.entities.Research;
import com.pointtech.server.repositories.ResearchRepository;

@Service
public class ResearchServices {

    @Autowired
    ResearchRepository researchRepository;

    public List<Research> findAll() {
        return researchRepository.findAll();
    }

    public Research findById(Long id) {
        return researchRepository.findById(id).get();
    }

    public Research insert(Research research) {
        return researchRepository.save(research);
    }

    public void delete(Long id){
        researchRepository.deleteById(id);
    }

    public Research update(Long id, Research research){
        Research entity = researchRepository.getReferenceById(id);
        updateData(entity, research);
        return researchRepository.save(entity);
    }

    private void updateData(Research entity, Research research) {
        entity.setDescription(research.getDescription());
        entity.setMoment(research.getMoment());
        entity.setTitle(research.getTitle());
    }
}
