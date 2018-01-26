package nl.sogyo.mancala;

public abstract class SuperCube {
	protected int stock = 4;
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
			switchTurn();
	}
	
	protected abstract int takeStones(int stones);
	protected abstract void switchTurn();
	
	protected void addStoneToStock() {
		stock++;
	}
	
	public MancalaPlayer getPlayer() {
		return myPlayer;
	}
}
