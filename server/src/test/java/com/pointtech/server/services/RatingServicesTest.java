package com.pointtech.server.services;

import com.pointtech.server.entities.Rating;
import com.pointtech.server.repositories.RatingRepository;
import com.pointtech.server.services.exceptions.ElementAlreadyExists;
import com.pointtech.server.services.exceptions.ElementNotFounded;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RatingServicesTest {

    @Mock
    private RatingRepository ratingRepository;
    private RatingServices serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new RatingServices(ratingRepository);
    }

    @Test
    void canGetAllRatings() {
       serviceTest.findAll();
        verify(ratingRepository).findAll();
    }

    @Test
    void canFindById() {
        long id = 1L;
        Optional<Rating> rating = Optional.of(new Rating(1L,8, "It's good"));
        given(ratingRepository.findById(id)).willReturn(rating);
        serviceTest.findById(id);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(ratingRepository).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void willThrowWhenFindByIdNotFoundOnFindById() {
        long id = 1L;
        given(ratingRepository.findById(anyLong())).willThrow(new NoSuchElementException());
        assertThatThrownBy(()->serviceTest.findById(id))
                .isInstanceOf(ElementNotFounded.class)
                .hasMessage("Element not founded! id: " + id);
    }
    @Test
    void canCreateRating() {
        Rating rating = new Rating(1L, 32, "It's good");
        serviceTest.insert(rating);
        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(ratingArgumentCaptor.capture());
        assertThat(ratingArgumentCaptor.getValue()).isEqualTo(rating);
    }

    @Test
    void willThrowElementAlreadyExistsWhenCreating(){
        Rating rating = new Rating(1L, 32, "It's good");
        given(ratingRepository.existsById(rating.getId())).willReturn(Boolean.TRUE);
        assertThatThrownBy(()->{
            serviceTest.insert(rating);
        })
                .isInstanceOf(ElementAlreadyExists.class)
                .hasMessage("User already exists!");
    }
    @Test
    void canDeleteRating() {
        long id = 1L;
        serviceTest.delete(id);
        verify(ratingRepository).deleteById(id);
    }
    @Test
    void willThrowElementNotFoundedOnDeleting(){
        long id = 1L;
        given(ratingRepository.findById(anyLong())).willThrow(new NoSuchElementException());
        assertThatThrownBy(()->serviceTest.findById(id))
                .isInstanceOf(ElementNotFounded.class)
                .hasMessage("Element not founded! id: " + id);
    }
    @Test
    void canUpdateRating() {
        Rating rating = new Rating(1L, 32, "It's good");
        Rating updatedRating = new Rating(1L, 72, "It's good");
        given(ratingRepository.getReferenceById(rating.getId())).willReturn(rating);
        serviceTest.update(1L, rating);
        ArgumentCaptor<Rating> argumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(updatedRating);
    }

    @Test
    void willThrowElementNotFoundedOnUpdate(){
        long id = 1L;
        Rating rating = new Rating(1L, 32, "It's good");
        given(ratingRepository.getReferenceById(id)).willThrow(new NoSuchElementException());
        assertThatThrownBy(()->serviceTest.update(id, rating))
                .isInstanceOf(ElementNotFounded.class)
                .hasMessage("Element not founded! id: " + id);
    }
}