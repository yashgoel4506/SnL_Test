package com.qait.automation.SnL_Test;

import java.util.UUID;
/**
 *
 * @author Ramandeep
 */
public class InvalidTurnException extends Exception{
    
    public InvalidTurnException(UUID playerUuid){
        super("Player '"+playerUuid.toString()+"' does not have the turn");
    }
}
