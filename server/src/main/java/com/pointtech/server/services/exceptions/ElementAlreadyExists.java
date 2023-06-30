package com.pointtech.server.services.exceptions;

public class ElementAlreadyExists extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ElementAlreadyExists(String message){
        super(message);
    }
}
