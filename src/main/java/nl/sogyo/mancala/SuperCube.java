package nl.sogyo.mancala;

public abstract class SuperCube {
	protected int stock;
	protected MancalaPlayer myPlayer;
	protected SuperCube neighbourCube;
	
	public int getStock() {
		return stock;
	}
	
	public SuperCube getCube(int cubeNumber) {
		return (cubeNumber == 0) ? this : neighbourCube.getCube(--cubeNumber); 
	}
	
	public void receiveStones(int stones) {
		stones = takeStones(stones);
		if (stones > 0)
			neighbourCube.receiveStones(stones);
		else
			endOfTurn();
	}
	
	protected int emptyStock() {
		int myStock = this.stock;
		this.stock = 0;
		return myStock;
	}
	
	protected abstract int takeStones(int stones);
	protected abstract void endOfTurn();
	protected abstract int captureOpponentStones(int index, int step);
	protected abstract boolean checkPlayability(SuperCube startingCube);
	
	protected void checkPlayability() {
		if (!neighbourCube.checkPlayability(this)) {
			neighbourCube.collectAllStones(this);
		}
	}
	
	protected void collectAllStones(SuperCube startingCube) {
		myPlayer.collectStones(emptyStock());
		if (this == startingCube) return;
		neighbourCube.collectAllStones(startingCube);
		myPlayer.decideWinnerAndLoser();
	}
	
	protected void addStoneToStock() {
		stock++;
	}
	
	public MancalaPlayer getPlayer() {
		return myPlayer;
	}
}
