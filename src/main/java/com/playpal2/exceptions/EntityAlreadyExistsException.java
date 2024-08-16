package com.playpal2.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(){
        super("La seguente entità è gia esistente");
    }
}
