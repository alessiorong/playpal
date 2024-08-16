package com.playpal2.exceptions;

import com.playpal2.model.entity.Player;
import com.playpal2.model.repository.PlayerRepo;

public class DuplicatePlayerException extends RuntimeException{
    public DuplicatePlayerException(Player duplicatePlayer){
        super("A player with name '"+ duplicatePlayer.getFirstname()+ "' and lastname '"+duplicatePlayer.getLastname()+ "' already exists");
    }
}
