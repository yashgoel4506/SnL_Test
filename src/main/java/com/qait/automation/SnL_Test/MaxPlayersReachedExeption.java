package com.qait.automation.SnL_Test;

/**
 *
 * @author Ramandeep
 */
public class MaxPlayersReachedExeption extends Exception{
    
    public MaxPlayersReachedExeption(Integer players){
        super("The board already has maximum allowed Player: " + players);
    }
}
