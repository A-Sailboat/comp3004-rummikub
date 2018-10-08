package core;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	
	public void testShuffle() {
		Deck deck = new Deck();
		assertEquals(true, deck.shuffle());
	}
	
	public void testDealTile() {
		Deck deck = new Deck();
		assertEquals(106, deck.size();
		deck.dealTile();
		assertEquals(105, deck.size());
	}
}
