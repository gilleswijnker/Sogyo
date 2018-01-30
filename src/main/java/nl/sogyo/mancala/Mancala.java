package nl.sogyo.mancala;

import java.io.Serializable;
import java.util.*;

/**
 * Interface to the Mancala game
 */

public final class Mancala implements Serializable{
	private Cube firstCube;
	
	////////////////
	// Constructor /
	////////////////
    public Mancala() {
    	firstCube = new Cube();
    }
    
    public boolean gameHasWinner() {
    	return firstCube.getPlayer().getState() != PlayerState.UNDECIDED;
    }
    
    public Map<Player, Integer> getWinInfo() {
    	Map<Player, Integer> result = new HashMap<>();
    	result.put(Player.PLAYER1, firstCube.getPlayer().getStockAtEndOfGame());
    	result.put(Player.PLAYER2, firstCube.getPlayer().getOpponent().getStockAtEndOfGame());
    	return result;
    }
    
    public Player getPlayersTurn() {
    	return (firstCube.getPlayer().hasTurn()) ? Player.PLAYER1 : Player.PLAYER2;
    }
    
    public int cubeGetStock(int i) {
    	return firstCube.getCube(i).getStock();
    }
    
    public void cubePlayStones(int i) {
    	((Cube) firstCube.getCube(i)).playStones();
    }
}
