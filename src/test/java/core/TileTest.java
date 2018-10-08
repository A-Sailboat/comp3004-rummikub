package core;

import junit.framework.TestCase;

public class TileTest extends TestCase{
	public void testNewTileTwoParameters() {
		Tile tile = new Tile(5, "blue");
		assertEquals(5, tile.getNumber());
		assertEquals("blue", tile.getColor());
	}
	
	public void testNewTileThreeParameters() {
		Tile tile = new Tile(8, "red", true);
		assertEquals(8, tile.getNumber());
		assertEquals("red", tile.getColor());
		assertEquals(true, tile.getVisibile());
	}
}
