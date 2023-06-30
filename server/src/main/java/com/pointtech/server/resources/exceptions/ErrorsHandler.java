package com.pointtech.server.resources.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pointtech.server.services.exceptions.DatabaseException;
import com.pointtech.server.services.exceptions.ElementAlreadyExists;
import com.pointtech.server.services.exceptions.ElementNotFounded;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;


import java.time.Instant;




@ControllerAdvice
public class ErrorsHandler {
    
    @ExceptionHandler(ElementNotFounded.class)
    public ResponseEntity<StandartException> standart(StandartException e, HttpServletRequest request){
        StandartException err = new StandartException();
        err.setTimestamp(Instant.now());
        err.setStatus(e.getStatus());
        err.setError(e.getError());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(e.getStatus()).body(err);
    }

    @ExceptionHandler(ElementAlreadyExists.class)
    public ResponseEntity<StandartException> standart(ElementAlreadyExists e, HttpServletRequest request){
        StandartException err = new StandartException();
        err.setTimestamp(Instant.now());
        err.setStatus(409);
        err.setError("Conflict");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(409).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartException> standart(DatabaseException e, HttpServletRequest request){
        StandartException err = new StandartException();
        err.setTimestamp(Instant.now());
        err.setStatus(500);
        err.setError("Database Error");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(500).body(err);
    }
    
}
