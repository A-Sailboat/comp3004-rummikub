package core;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class BoardTest extends TestCase{
	
	public void testToString() {
		
	
		Board board = new Board();
		
		Meld meld1 = new Meld();
		
		meld1.add(new Tile(13, "BLUE"));
		meld1.add(new Tile(13, "RED"));
		meld1.add(new Tile(13, "ORANGE"));
		
		Meld meld2 = new Meld();
		
		meld2.add(new Tile(4, "ORANGE"));
		meld2.add(new Tile(3, "ORANGE"));
		meld2.add(new Tile(6, "ORANGE"));
		meld2.add(new Tile(5, "ORANGE"));
		
		String sampleOutput ="\t\t1\t2\3\t4\nl1\tGROUP\tB13\tR13\tO13\nl\tRUN\tO3\tO4\tO5\tO6\nl";
		System.out.print(sampleOutput);
		assertTrue(board.toString() == sampleOutput);
		}
	
	}