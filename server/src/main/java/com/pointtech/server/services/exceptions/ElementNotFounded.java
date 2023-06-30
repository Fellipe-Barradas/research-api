package com.pointtech.server.services.exceptions;

public class ElementNotFounded extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ElementNotFounded(long id){
        super("Element not founded! id: " + id);
    }
    
}
