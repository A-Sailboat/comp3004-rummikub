package core;

import junit.framework.TestCase;

public class TileTest extends TestCase{
	public void testNewTileTwoParameters() {
		Tile tile = new Tile(5, "BLUE");
		assertEquals(5, tile.getNumber());
		assertEquals("blue", tile.getColor());
	}
	
	public void testNewTileThreeParameters() {
		Tile tile = new Tile(8, "RED", true);
		assertEquals(8, tile.getNumber());
		assertEquals("red", tile.getColor());
		assertEquals(true, tile.getVisibile());
	}
	
	public void testTileComparator() {
		Tile tile1 = new Tile(7, "RED");
		Tile tile2 = new Tile(8, "RED");
		Tile tile3 = new Tile(8, "RED");
		Tile tile4 = new Tile(12, "RED");

		assertTrue(tile1.compareTo(tile2) < 0);
		assertTrue(tile2.compareTo(tile3) == 0);
		assertTrue(tile3.compareTo(tile4) < 0);
		assertTrue(tile4.compareTo(tile1) > 0);
		
	
	}
	public void testTileComparatorColor() {
		Tile tile1 = new Tile(8, "RED");
		Tile tile2 = new Tile(8, "BLUE");
		Tile tile3 = new Tile(8, "GREEN");
		Tile tile4 = new Tile(8, "ORANGE");
				
		assertTrue(tile2.compareTo(tile3) > 0);
		assertTrue(tile3.compareTo(tile4) > 0);
		assertTrue(tile4.compareTo(tile1) > 0);
	
	}
}
