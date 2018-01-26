package nl.sogyo.mancala;

public class MancalaPlayer {
	private MancalaPlayer opponent;
	private boolean isItMyTurn;
	private int stockAtEndOfGame = 0;
	private PlayerState state = PlayerState.UNDECIDED;
	
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
	
	public void collectStones(int stones) {
		stockAtEndOfGame += stones;
	}
	
	public int getStockAtEndOfGame() {
		return stockAtEndOfGame;
	}
	
	public void decideWinnerAndLoser() {
		if (state != PlayerState.UNDECIDED) return;
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
