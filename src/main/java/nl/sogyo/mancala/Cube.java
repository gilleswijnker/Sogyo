package nl.sogyo.mancala;

public final class Cube extends SuperCube{
	/**
	 * First cube that is created. This cube will create its player and its
	 * neighbour cube.
	 */
	public Cube() {
		this(13, 4);
	}
	
	public Cube(int neighboursRemaining, MancalaPlayer player, Cube firstCube, int boardSize, int stockSize) {
		this.stock = stockSize;
		this.myPlayer = player;
		this.neighbourCube  = (--neighboursRemaining % ((boardSize + 1) / 2) == 0) ? 
						new Kalaha(neighboursRemaining, player, firstCube, boardSize, stockSize) : 
						new Cube(neighboursRemaining, player, firstCube, boardSize, stockSize);
	}
	
	public Cube(int boardSize, int stockSize) {
		this.stock = stockSize;
		this.myPlayer = new MancalaPlayer();
		this.neighbourCube = new Cube(boardSize - 1, this.myPlayer, this, boardSize, stockSize);
	}
	
	public void playStones() { 
		if (!this.myPlayer.hasTurn() || this.stock == 0)
			throw new InvalidMoveException();
		int stones = emptyStock();
		this.neighbourCube.receiveStones(stones);
	}
	
	@Override
	protected int takeStones(int stones) {
		addStoneToStock();
		return stones - 1;
	}
	
	@Override
	protected void endOfTurn() {
		if (this.myPlayer.hasTurn() && this.stock == 1) {
			int capturedStones = this.captureOpponentStones(0, 1);
			if (capturedStones > 0)
				emptyStock();
		}
		switchTurn();
	}
	
	@Override
	protected int captureOpponentStones(int index, int step) {
		index += step;
		return (index == 0) ? emptyStock() : this.neighbourCube.captureOpponentStones(index, step);
	}
	
	protected void switchTurn() {
		this.myPlayer.switchTurn();
		this.checkPlayability();
	}
	
	@Override
	protected boolean checkPlayability(SuperCube startingCube) {
		if (this == startingCube) return false;
		return (this.myPlayer.hasTurn() && this.stock > 0) ?
			true :
			this.neighbourCube.checkPlayability(startingCube);
	}
}
