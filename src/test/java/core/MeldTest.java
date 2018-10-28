package core;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class MeldTest extends TestCase{
	
	public void testGroup() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(13, "red"));
		meld.add(new Tile(13, "orange"));
		assertTrue(meld.validate());
	}
	
	public void testRun() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(12, "BLUE"));
		meld.add(new Tile(11, "BLUE"));
		assertTrue(meld.validate());
	}	
	public void testShortRun() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(12, "BLUE"));

		assertFalse(meld.validate());
	}
	
	public void testShortGroup() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(13, "red"));
		assertFalse(meld.validate());
	}
	
	public void testIncorrectGroup() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(13, "red"));
		meld.add(new Tile(13, "orange"));
		assertFalse(meld.validate());
	}
	
	public void testIncorrectRun() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(12, "BLUE"));
		meld.add(new Tile(11, "BLUE"));
		assertFalse(meld.validate());
	}
	
	public void testGroupCatagorization() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(13, "red"));
		meld.add(new Tile(13, "orange"));
		assertTrue(meld.determineType() == "GROUP");
	}
	
	public void testRunCatagorization() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(12, "BLUE"));
		meld.add(new Tile(11, "BLUE"));
		assertTrue(meld.determineType() == "RUN");
	}
	
	public void testToString() {
		Meld meld = new Meld();
		meld.add(new Tile(13, "BLUE"));
		meld.add(new Tile(13, "RED"));
		meld.add(new Tile(13, "ORANGE"));
		
		assertTrue(meld.toString() == "GROUP\tB13\tR13\tO13");
	}
}