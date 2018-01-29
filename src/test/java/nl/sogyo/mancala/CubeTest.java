package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class CubeTest {

	/**
	 * Testing the setup of the board
	 */ 
	@Test
	public void doesACubeStartWithFourStones() {
		Cube cube = new Cube();
		int stones = cube.getStock();
		Assert.assertEquals(4, stones);
	}
	
	@Test
	public void doesACubeCreateItsNeighbour() {
		Cube cube = new Cube();
		Cube neighbourCube = (Cube) cube.getCube(1);
		Assert.assertNotNull(neighbourCube);
	}
	
	@Test
	public void doesGetCubeGetTheCorrectFirstCube() {
		Cube cube = new Cube();
		Cube neighbourCube = (Cube) cube.getCube(0);
		Assert.assertTrue(cube == neighbourCube);
	}
	
	@Test
	public void isTheBoardCircular() {
		Cube cube = new Cube();
		// cube 15 is the first cube after the second kalaha
		// so should be the same as the first cube of the board
		Cube neighbourCube = (Cube) cube.getCube(14);
		Assert.assertTrue(cube == neighbourCube);
	}
	
	@Test
	public void doesTheOwnerOfACubeSwitchAfterTheKalaha() {
		Cube cube = new Cube();
		MancalaPlayer player1 = cube.getPlayer();
		// cube 8 is the first cube after the first kalaha
		MancalaPlayer player2 =  cube.getPlayer().getOpponent();
		Assert.assertFalse(player1 == player2);			
	}
	
	/**
	 * Testing execution of a turn
	 * Player 1 (owner of the first cube) always starts
	 */
	@Test(expected = InvalidMoveException.class)
	public void isACubeOnlyPlayableWhenItsOwnerHasTurn() {
		// cube 7 is the first cube of the opponent
		Cube cube = (Cube) new Cube().getCube(7);
		cube.playStones();
	}
	
	@Test
	public void isCubeEmptiedUponPlay() {
		Cube cube = (Cube) new Cube();
		cube.playStones();
		Assert.assertEquals(0, cube.getStock());
	}
	
	@Test
	public void areStonesPassedToNeighbourUponPlay() {
		Cube cube = (Cube) new Cube();
		Cube cube2 = (Cube) cube.getCube(1);
		cube.playStones();
		Assert.assertEquals(5, cube2.getStock());
	}
	
	@Test
	public void areStonesPassedUpToLastNeighbourUponPlay() {
		Cube cube = (Cube) new Cube();
		Cube cube4 = (Cube) cube.getCube(4);
		cube.playStones();
		Assert.assertEquals(5, cube4.getStock());
	}
	
	@Test
	public void areStonesNotPassedFurtherThanLastNeighbourUponPlay() {
		Cube cube = (Cube) new Cube();
		Cube cube5 = (Cube) cube.getCube(5);
		cube.playStones();
		Assert.assertEquals(4, cube5.getStock());
	}
	
	@Test
	public void isPlayerThatHasTurnSwitchedAfterATurnEndsInCube() {
		Cube cube = (Cube) new Cube();
		cube.playStones();
		Assert.assertFalse(cube.getPlayer().hasTurn());
	}
	
	/**
	 * Test capture of opponent stones
	 */
	@Test
	public void doesCaptureTheOpponentWork() {
		Cube cube = new Cube();
		// Player 1 plays 5th cube
		// 4 4 4 4 5 5
		// 4 4 4 4 0 5
		((Cube) cube.getCube(4)).playStones();
		// Player 2 plays 2nd cube, ending in his Kalaha
		// 5 5 5 5 0 5
		// 4 4 4 4 0 5
		((Cube) cube.getCube(8)).playStones();
		// Player 2 plays 1st cube
		// 6 6 6 6 1 0
		// 4 4 4 4 0 5
		((Cube) cube.getCube(7)).playStones();
		// Player 1 plays 1st cube, capturing stones from player 2's 2nd cube
		// 6 6 6 6 1 0  --> 6 6 6 6 0 0
		// 0 5 5 5 1 5  	0 5 5 5 0 5
		cube.playStones();
		Assert.assertEquals(3, cube.getCube(6).getStock());
	}
	
	@Test
	public void doesSuccessfullCaptureEmptyOwnCube() {
		Cube cube = new Cube();
		// Player 1 plays 5th cube
		// 4 4 4 4 5 5
		// 4 4 4 4 0 5
		((Cube) cube.getCube(4)).playStones();
		// Player 2 plays 2nd cube, ending in his Kalaha
		// 5 5 5 5 0 5
		// 4 4 4 4 0 5
		((Cube) cube.getCube(8)).playStones();
		// Player 2 plays 1st cube
		// 6 6 6 6 1 0
		// 4 4 4 4 0 5
		((Cube) cube.getCube(7)).playStones();
		// Player 1 plays 1st cube, capturing stones from player 2's 2nd cube
		// 6 6 6 6 1 0  --> 6 6 6 6 0 0
		// 0 5 5 5 1 5  	0 5 5 5 0 5
		cube.playStones();
		Assert.assertEquals(0, cube.getCube(4).getStock());
	}
	
	/**
	 * Test end of game
	 */
	@Test
	public void doesGameSignalAWinner() {
		Cube cube = new Cube(5, 1);
		// Player 1 plays 2nd cube
		// 1 1
		// 1 0
		((Cube) cube.getCube(1)).playStones();
		// Player 1 plays 1st cube (note the capture)
		// 1 1 --> 1 0
		// 0 1	   0 0
		cube.playStones();
		// Player 2 plays 2nd cube, and then has another turn but no moves left
		// 0 0
		// 0 0
		((Cube) cube.getCube(4)).playStones();
		// Player 1 has 3 stones in his Kalaha, player 2 has 1 stone.
		// So player 1 is the winner
		Assert.assertEquals(PlayerState.WIN , cube.getPlayer().getState());
	}
	
	@Test
	public void areAllStonesAddedToPlayersStockAtEndOfGame() {
		Cube cube = new Cube(5, 1);
		// Player 1 plays 2nd cube
		// 1 1
		// 1 0
		((Cube) cube.getCube(1)).playStones();
		// Player 1 plays 1st cube (note the capture)
		// 1 1 --> 1 0
		// 0 1	   0 0
		cube.playStones();
		// Player 2 plays 2nd cube, and then has another turn but no moves left
		// 0 0
		// 0 0
		((Cube) cube.getCube(4)).playStones();
		// Player 1 has 3 stones in his Kalaha, player 2 has 1 stone.
		// So player 1 is the winner
		Assert.assertEquals(cube.getPlayer().getStockAtEndOfGame(), 3);
	}
}
