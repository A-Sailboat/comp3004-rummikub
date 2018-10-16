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
		Tile tile = new Tile();
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
		Tile tile = new Tile();
		Tile tile1 = new Tile();
		Tile tile2 = new Tile();
		hand.addTile(tile);
		hand.addTile(tile);
		hand.addTile(tile);
		assertEquals("B2, G3, G9", hand.printHand());
	}

}
