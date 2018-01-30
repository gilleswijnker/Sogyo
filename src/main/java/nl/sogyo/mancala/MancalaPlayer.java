package nl.sogyo.mancala;

import java.io.Serializable;

public class MancalaPlayer implements Serializable {
	private MancalaPlayer opponent;
	private boolean isItMyTurn;
	private int stockAtEndOfGame = 0;
	private PlayerState state = PlayerState.UNDECIDED;
	
	////////////////
	// Constructor /
	////////////////
	public MancalaPlayer() {
		isItMyTurn = true;
		opponent = new MancalaPlayer(this, !isItMyTurn);
	}
	
	public MancalaPlayer(MancalaPlayer opponent, boolean isItMyTurn) {
		this.opponent = opponent;
		this.isItMyTurn = isItMyTurn; 
	}

	/////////////////////////////
	// Methods to play the game / 
	/////////////////////////////
	public MancalaPlayer getOpponent() {
		return opponent;
	}

	public boolean hasTurn() {
		return isItMyTurn;
	}
	
	// switch both players turn
	public void switchTurn() {
		switchMyTurn();
		opponent.switchMyTurn();
	}
	
	// switch only turn of this player
	protected void switchMyTurn() {
		isItMyTurn = !isItMyTurn;
	}
	
	public void collectStones(int stones) {
		stockAtEndOfGame += stones;
	}
	
	public int getStockAtEndOfGame() {
		return stockAtEndOfGame;
	}
	
	public void decideWinnerAndLoser() {
		if (state != PlayerState.UNDECIDED) return; // decision has already been made
		if (stockAtEndOfGame > opponent.getStockAtEndOfGame())
			state = PlayerState.WIN;
		else if (stockAtEndOfGame == opponent.getStockAtEndOfGame())
			state = PlayerState.DRAW;
		else
			state = PlayerState.LOOSE;
		opponent.decideWinnerAndLoser();
	}
	
	public PlayerState getState() {
		return state;
	}
}
