package nl.sogyo.mancala;

public final class Cube extends SuperCube{
	////////////////
	// Constructor /
	////////////////
	
	// default cube
	public Cube() {
		this(13, 4);
	}
	
	
	// custom cube
	public Cube(int boardSize, int stockSize) {
		this.stock = stockSize;
		this.myPlayer = new MancalaPlayer();
		createNeighbour(boardSize, this.myPlayer, this, boardSize, stockSize);
	}
	
	// 'iterative' created cubes 
	public Cube(int neighboursRemaining, MancalaPlayer player, Cube firstCube, int boardSize, int stockSize) {
		this.stock = stockSize;
		this.myPlayer = player; 
		createNeighbour(neighboursRemaining, player, firstCube, boardSize, stockSize);
	}
	
	// create neighbour
	private void createNeighbour(int neighboursRemaining, MancalaPlayer player, Cube firstCube, int boardSize, int stockSize) {
		this.neighbourCube  = (--neighboursRemaining % ((boardSize + 1) / 2) == 0) ? 
				new Kalaha(neighboursRemaining, player, firstCube, boardSize, stockSize) : 
				new Cube(neighboursRemaining, player, firstCube, boardSize, stockSize);
	}
	
	/////////////////////////////
	// Methods to play the game /
	/////////////////////////////
	
	// choose this cube to be played
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
			// capture opponent stones
			int capturedStones = captureOpponentStones(0, 1);
			
			/* when there are stones captured from the opponent, the Kalaha
			 * has already added the single stone of this cube to its stock.
			 * So then empty this cube */
			if (capturedStones > 0)
				emptyStock();
		}
		switchTurn();
	}
	
	// capture the stones from the opponent's cube lying across this cube 
	@Override
	protected int captureOpponentStones(int index, int step) {
		// step is +1 up to the Kalaha, and -1 upward of the Kalaha
		index += step;
		
		// if index == 0, then we've reached the opponent's cube lying across
		return (index == 0) ? emptyStock() : this.neighbourCube.captureOpponentStones(index, step);
	}
	
	protected void switchTurn() {
		this.myPlayer.switchTurn();
		this.checkPlayability();
	}
	
	// check if player that is on turn can still make a move
	@Override
	protected boolean checkPlayability(SuperCube startingCube) {
		if (this == startingCube) return false; // we've been round the board and did not find a playable cube
		return (this.myPlayer.hasTurn() && this.stock > 0) ?
			true :
			this.neighbourCube.checkPlayability(startingCube);
	}
}
