package com.pointtech.server.services;

import com.pointtech.server.entities.Image;
import com.pointtech.server.entities.Research;
import com.pointtech.server.repositories.ResearchRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResearchServicesTest {

    @Mock
    private ResearchRepository researchRepository;
    private ResearchServices researchServices;


    @BeforeEach
    void setUp() {
        researchServices = new ResearchServices(researchRepository);
    }

    @Test
    void canFindAll() {
        researchServices.findAll();
        verify(researchRepository).findAll();
    }

    @Test
    void canFindResearchById() {
        long id = 1L;
        Image image = new Image("https://imagem.png", "imagem de computador");
        Research research = new Research(1L,"Front-end","Carreira paia",Instant.now(),image);
        given(researchRepository.findById(id)).willReturn(Optional.of(research));
        researchServices.findById(id);
        verify(researchRepository).findById(id);
    }

    @Test
    void canCreateResearch() {
        Image image = new Image("https://imagem.png", "imagem de computador");
        Research research = new Research(1L,"Front-end","Carreira paia",Instant.now(),image);
        researchServices.insert(research);
        ArgumentCaptor<Research> argumentCaptor = ArgumentCaptor.forClass(Research.class);
        verify(researchRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(research);
    }

    @Test
    void canDeleteById() {
        long id = 1L;
        researchServices.delete(id);
        verify(researchRepository).deleteById(id);
    }

    @Test
    void update() {
        Image image = new Image("https://imagem.png", "imagem de computador");
        Research research = new Research(5L,"Back-end","Carreira paia",Instant.now(),image);
         Research updatedResearch = new Research(5L,
                "Front-end",
                "Carreira top",Instant.now(),image);
        given(researchRepository.getReferenceById(5L)).willReturn(research);
        researchServices.update(5L, updatedResearch);
        ArgumentCaptor<Research> argumentCaptor = ArgumentCaptor.forClass(Research.class);
        verify(researchRepository).save(argumentCaptor.capture());
        Research capturedValue = argumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(updatedResearch);
    }
}