package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaPlayerTest {

	/**
	 * Creation of player and opponent
	 */
    @Test
    public void doesFirstPlayerCreateAnotherPlayer() {
    	MancalaPlayer player = new MancalaPlayer();
    	MancalaPlayer opponent = player.getOpponent();
    	Assert.assertTrue(opponent instanceof MancalaPlayer);
    }
    
    @Test
    public void doesFirstPlayerNotReturnHimself() {
    	MancalaPlayer player = new MancalaPlayer();
    	MancalaPlayer opponent = player.getOpponent();
    	Assert.assertFalse(opponent.equals(player));
    }
    
    @Test
    public void doesOpponentKnowFirstPlayer() {
    	MancalaPlayer player = new MancalaPlayer();
    	MancalaPlayer opponent = player.getOpponent();
    	Assert.assertEquals(opponent.getOpponent(), player);
    }
    
    @Test
    public void isThereASingleStartingPlayer() {
    	MancalaPlayer player = new MancalaPlayer();
    	MancalaPlayer opponent = player.getOpponent();
    	Assert.assertTrue(player.hasTurn() ^ opponent.hasTurn());
    }
    
    /**
     * Switching turns
     */
    @Test
    public void canAPlayerSwitchTurn() {
    	MancalaPlayer player = new MancalaPlayer();
    	boolean playerHasTurn = player.hasTurn();
    	player.switchTurn();
    	Assert.assertNotEquals(playerHasTurn, player.hasTurn());
    }
    
    @Test
    public void doesOpponentSwitchTurnWhenPlayerSwitchesTurn() {
    	MancalaPlayer player = new MancalaPlayer();
    	MancalaPlayer opponent = player.getOpponent();
    	boolean opponentHasTurn = opponent.hasTurn();
    	player.switchTurn();
    	Assert.assertNotEquals(opponentHasTurn, opponent.hasTurn());
    }
}
