package nl.sogyo.mancala;

public final class Kalaha extends SuperCube {
	public Kalaha(int neighboursRemaining, MancalaPlayer player, Cube firstCube, int boardSize, int stockSize) {
		this.stock = 0;
		this.myPlayer = player;
		
		// When there are no neighbours remaining, then the first cube must be this Kalaha's neighbour
		this.neighbourCube = (neighboursRemaining == 0) ?
				firstCube :
				new Cube(--neighboursRemaining, player.getOpponent(), firstCube, boardSize, stockSize);
	}
	
	@Override
	protected int takeStones(int stones) {
		if (myPlayer.hasTurn()) {
			addStoneToStock();
			stones--;
		}
		return stones;
	}
	
	@Override
	protected void endOfTurn() {
		this.checkPlayability();
	}	
	
	@Override
	protected int captureOpponentStones(int index, int step) {
		// set 'step' to -1, so cubes can now subtract index until it reaches zero 
		int capturedStones = this.neighbourCube.captureOpponentStones(index, -1);
		this.stock += capturedStones;
		
		// Add the stone that's in the player's cube
		if (capturedStones > 0) this.stock++;
		return capturedStones;
	}
	
	@Override
	protected boolean checkPlayability(SuperCube startingCube) {
		if (this == startingCube) return false;
		return this.neighbourCube.checkPlayability(startingCube);
	}
}
