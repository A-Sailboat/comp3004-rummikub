package core;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HandTest extends TestCase{
	
	public void testAddTile() {
		Hand hand = new Hand();
		Tile tile = new Tile();
		assertEquals(true, hand.addTile(tile));
	}
	
	public void testPrintHand() {
		Hand hand = new Hand();
		Tile tile = new Tile(8, "B", true);
		hand.addTile(tile);
		assertEquals("B8", hand.printHand());
	}
	
	public void testRemoveTile() {
		Hand hand = new Hand();
		Tile tile = new Tile();
		hand.addTile(tile);
		assertEquals(true, hand.removeTile(tile));
	}
	
	public void testArrangeHand() {
		Hand hand = new Hand();
		Tile tile = new Tile(9,"G", true);
		Tile tile1 = new Tile(2, "B", true);
		Tile tile2 = new Tile(3, "G", true);
		hand.addTile(tile);
		hand.addTile(tile1);
		hand.addTile(tile2);
		hand.arrangeHand();
		assertEquals("B 2, G 3, G 9", hand.printHand());
	}

}
