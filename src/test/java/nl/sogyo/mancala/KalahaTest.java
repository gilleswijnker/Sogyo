package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {

	/**
	/* Testing the setup of the board
	 */
	@Test
	public void isTheSeventhCubeAKalaha() {
		// getCube is zero based
		Cube cube = new Cube();
		SuperCube neighbourCube = cube.getCube(6);
		Assert.assertTrue(neighbourCube instanceof Kalaha);
	}
	
	@Test
	public void isTheFourteenthCubeAKalaha() {
		//getCube is zero based
		Cube cube = new Cube();
		SuperCube neighbourCube = cube.getCube(13);
		Assert.assertTrue(neighbourCube instanceof Kalaha);
	}
	
	/**
	 * Test game play
	 */
	@Test
	public void isPlayerThatHasTurnNotSwitchedAfterATurnEndsInKalaha() {
		Cube cube = (Cube) new Cube().getCube(2);
		cube.playStones();
		Assert.assertTrue(cube.getPlayer().hasTurn());
	}
	
	@Test
	public void isKalahaOfOpponentIgnored() {
		// Create a board with six fields
		Cube cube = new Cube(5, 4);
		// Next moves skips the opponents Kalaha.
		// 4 4 --> 5 5
		// 4 4	   5 0
		((Cube) cube.getCube(1)).playStones();
		Assert.assertEquals(5, cube.getStock());
	}
}
