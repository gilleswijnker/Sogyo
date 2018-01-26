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
		Cube neighbourCube = (Cube) cube.getCube(2);
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
		MancalaPlayer player2 =  cube.getCube(7).getPlayer();
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
	
//	@Test
//	public void doesKalahaOfOpponentNotPickAStone() {
//		Cube cube = new Cube();
//		((Cube) cube.getCube(11)).playStones();
//		System.out.println(cube.getCube(13).getStock());
//		Assert.assertEquals(0, cube.getCube(13).getStock());		
//	}
}
