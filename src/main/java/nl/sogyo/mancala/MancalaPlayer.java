package nl.sogyo.mancala;

public class MancalaPlayer {
	private MancalaPlayer opponent;
	private boolean isItMyTurn;
	
	public MancalaPlayer() {
		isItMyTurn = true;
		opponent = new MancalaPlayer(this, !isItMyTurn);
	}
	
	public MancalaPlayer(MancalaPlayer opponent, boolean isItMyTurn) {
		this.opponent = opponent;
		this.isItMyTurn = isItMyTurn; 
	}
	
	public MancalaPlayer getOpponent() {
		return opponent;
	}

	public boolean hasTurn() {
		return isItMyTurn;
	}
	
	public void switchTurn() {
		switchMyTurn();
		opponent.switchMyTurn();
	}
	
	public void switchMyTurn() {
		isItMyTurn = !isItMyTurn;
	}
}
