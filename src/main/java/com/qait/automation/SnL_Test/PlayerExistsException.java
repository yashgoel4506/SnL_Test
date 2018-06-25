package com.qait.automation.SnL_Test;

/**
 *
 * @author Ramandeep
 */
public class PlayerExistsException extends Exception{
    
    public PlayerExistsException(String name){
        super("Player '"+name+"' already exists on board");
    }
}
