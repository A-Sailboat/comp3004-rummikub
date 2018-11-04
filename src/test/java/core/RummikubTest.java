
package core;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;
import junit.framework.TestCase;

public class RummikubTest extends TestCase{
		
	public void testFileMode() {
		Rummikub game =  new Rummikub("baseCase.txt");
		assertTrue(game.play() == 0);
		
	}
	public void testConsole () {
		Rummikub game =  new Rummikub("baseCase.txt");
		game.play();
		
		//test for if deck deals all players 14
		AssetTrue(game.getPlayers().get(0).getStrategy() == "HUMAN");
		AssetTrue(game.getPlayers().get(1).getStrategy == "1");
		AssetTrue(game.getPlayers().get(2).getStrategy == "2");
		AssetTrue(game.getPlayers().get(3).getStrategy == "3");
		
		assertTrue(game.getPlayers().get(0).getHand().size() == 14 && p.get(1).getHand().size() == 14 && p.get(2).getHand().size() == 14 && p.get(3).getHand().size() == 14);
		
		for(int i = 0; i<4; i++) {
			/////////////////////////////////////////////////////////////??THIS NEEDS SOMME  CODE?>#@#@#!@#!@#!@#@!
			Hand handCopy = game.getPlayers().get(i).getHand();
			Collections.sortgame.getPlayers().get(i).getHand().
			assertTrue();
		}
		
	}
	public void requirementTwo() {
		Rummikub game =  new Rummikub();
		LinkedList<String> commandQueue = new LinkedList<String>;
		
		
		
		Rummikub testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,0);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "HUMAN");
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,1);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "1");
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,2);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "2");
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,3);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "3");
		
		//Test for ui elements 
		assertTrue(testGame.toString().contains("Computer 3's Turn"));
		
		assertTrue(forgame.getPlayers());
	}
	
	public void requirementThree() {
		Rummikub game =  new Rummikub();
		LinkedList<String> commandQueue = new LinkedList<String>;
		
		
		
		Rummikub testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,0);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "HUMAN");
		assertTrue(testGame.toString().contains("Human Turn"));
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,1);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "1");
		assertTrue(testGame.toString().contains("Computer 1 Turn"));
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,2);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "2");
		assertTrue(testGame.toString().contains("Computer 2 Turn"));
		
		testGame = game;
		commandQueue.offer("");
		testGame.play(commandQueue,3);
		AssertTrue(game.getCurrentPlayer().getStrategy() == "3");
		assertTrue(testGame.toString().contains("Computer 3 Turn"));
		
		assertTrue(forgame.getPlayers());///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void requirementFour() {
		Rummikub game =  new Rummikub();
		LinkedList<String> commandQueue = new LinkedList<String>();
		
		//
		Deck testDeck = new Deck("R1 R2 R3 R4 R5 R6 R7 O10 G10 R10 R11 R12 R13 R1 "
								+"R2 R3 R4 R5 R6 R7 R8 R9 R10 R11 R12 B1 B2 B3 "
								+"R13 B4 B5 B6 B7 B8 B9 B10 B11 B12 B13 B1 B2 B3 "
								+"B4 B5 B6 B7 B8 B9 B10 B11 B12 B13 O1 O2 O3 O4 "
								+"O5 O6 O7 O8 O9 O10 O11 O12 O13 O1 O2 O3 O4 O5 "
								+"O6 O7 O8 O9 O10 O11 O12 O13 G1 G2 G3 G4 G5 G6 "
								+"G7 G8 G9 G10 G11 G12 G13 G1 G2 G3 G4 G5 G6 G7 "
								+"G8 G9 G10 G11 G12 G13");
				
		game.setDeck(testDeck);
		
		
		Rummikub testGame = game;
		commandQueue.offer("");
		
		AssertTrue(testGame.play(commandQueue) == 0);
		AssertTrue(game.getHuman().playedInitialThirty());
		
		
		commandQueue = new LinkedList<String>();
		testDeck = new Deck("R1 R2 R3 R4 R5 R6 R7 O10 G10 R11 R11 R12 R13 R1 "
								+"R2 R3 R4 R5 R6 R7 R8 R9 R10 R11 R12 B1 B2 B3 "
								+"R13 B4 B5 B6 B7 B8 B9 B10 B11 B12 B13 B1 B2 B3 "
								+"B4 B5 B6 B7 B8 B9 B10 B11 B12 B13 O1 O2 O3 O4 "
								+"O5 O6 O7 O8 O9 O10 O11 O12 O13 O1 O2 O3 O4 O5 "
								+"O6 O7 O8 O9 O10 O11 O12 O13 G1 G2 G3 G4 G5 G6 "
								+"G7 G8 G9 G10 G11 G12 G13 G1 G2 G3 G4 G5 G6 G7 "
								+"G8 G9 G10 G11 G12 G13");
				
		game.setDeck(testDeck);
		
		
		Rummikub testGame = game;
		commandQueue.offer("H8 H9 H10 Meld");

		AssertTrue(testGame.play(commandQueue) == 0);
		AssertTrue(game.getHuman().playedInitialThirty());
		
		
		
		assertTrue(forgame.getPlayers());
	}
	
	
}
