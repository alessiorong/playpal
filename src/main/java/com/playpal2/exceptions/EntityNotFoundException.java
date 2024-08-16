package com.playpal2.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class<?> entityClass, long id){
        super(String.format("La entity %s con id %d non esiste", entityClass.getSimpleName(), id));
    }
}
