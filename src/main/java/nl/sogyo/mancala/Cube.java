package nl.sogyo.mancala;

public final class Cube extends SuperCube{
	/**
	 * First cube that is created. This cube will create its player and its
	 * neighbour cube.
	 */
	public Cube() {
		this.myPlayer = new MancalaPlayer();
		
		// neighboursRemaining is zero based, so there are 12 neighbours remaining
		this.neighbourCube = new Cube(12, this.myPlayer, this);
	}
	
	public Cube(int neighboursRemaining, MancalaPlayer player, Cube firstCube) {
		this.myPlayer = player;
		this.neighbourCube  = (--neighboursRemaining % 7 == 0) ? 
						new Kalaha(neighboursRemaining, player, firstCube) : 
						new Cube(neighboursRemaining, player, firstCube);
	}
	
	public void playStones() { 
		if (!this.myPlayer.hasTurn() || this.stock == 0)
			throw new InvalidMoveException();
		int stones = emptyStock();
		this.neighbourCube.receiveStones(stones);
	}
	
	private int emptyStock() {
		int myStock = this.stock;
		this.stock = 0;
		return myStock;
	}
	
	@Override
	protected int takeStones(int stones) {
		addStoneToStock();
		return stones - 1;
	}
	
	@Override
	protected void switchTurn() {
		myPlayer.switchTurn();
	}
}
