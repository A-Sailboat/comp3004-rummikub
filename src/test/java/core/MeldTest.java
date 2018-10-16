package core;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class MeldTest extends TestCase{
	
	public void testGroup() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "blue"));
		meld.add(new Tile(13, "red"));
		meld.add(new Tile(13, "orange"));
		assertTrue(meld.validate());
	}
	
	public void testRun() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "blue"));
		meld.add(new Tile(12, "blue"));
		meld.add(new Tile(11, "blue"));
		assertTrue(meld.validate());
	}
}