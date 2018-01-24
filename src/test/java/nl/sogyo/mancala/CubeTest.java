package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class CubeTest {

	@Test
	public void doesACubeStartWithFourStones() {
		Cube cube = new Cube();
		int stones = cube.getStock();
		Assert.assertEquals(4, stones);
	}
	
	@Test
	public void doesACubeCreateItsNeighbour() {
		
	}
}
