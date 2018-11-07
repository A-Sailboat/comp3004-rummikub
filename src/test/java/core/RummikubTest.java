
package core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;
import junit.framework.TestCase;

public class RummikubTest extends TestCase{
		
	public void testRequirementOne() throws IOException {
		Rummikub game;
		game = new Rummikub("requirement1/baseCase.txt");
		game.play();
		
		for(Player p: game.getPlayers()) {
			Hand q = p.getHand();
			Collections.sort(p.getHand().getHand());
			assertTrue(q.getHand().equals(p.getHand().getHand()));
		}
		
		
	}
	
	public void testRequirementTwo() throws IOException {
		Rummikub game1 =  new Rummikub("requirement2/oneTurnCase.txt");
		Rummikub game2 =  new Rummikub("requirement2/twoTurnCase.txt");
		Rummikub game3 =  new Rummikub("requirement2/threeTurnCase.txt");
		Rummikub game4 =  new Rummikub("requirement2/fourTurnCase.txt");
		
		game1.play();
		game2.play();
		game3.play();
		game4.play();
		
		assertTrue((game1.getPlayers().get((game1.getTurnNumber()-1)%4)) instanceof Human);
		assertTrue((game2.getPlayers().get((game2.getTurnNumber()-1)%4)).aiType instanceof OneAi);
		assertTrue((game3.getPlayers().get((game3.getTurnNumber()-1)%4)).aiType instanceof TwoAi);
		assertTrue((game4.getPlayers().get((game4.getTurnNumber()-1)%4)).aiType instanceof ThreeAi);
	}
	
	public void testRequirementThree() throws IOException {
		Rummikub game =  new Rummikub("requirement3/playerCanMeld.txt");
		
		game.play();
		
		assertTrue(game.getBoard().getMeldsOnBoard().size() > 0);
	}

	public void testRequirementFour() throws IOException {
		
		Rummikub game1 =  new Rummikub("requirement4/humanThirtyPointOneMeld.txt");
		Rummikub game2 =  new Rummikub("requirement4/humanThirtyPlusPointOneMeld.txt");
		Rummikub game3 =  new Rummikub("requirement4/humanMultiMeldThirtyPoints.txt");
		Rummikub game4 =  new Rummikub("requirement4/humanMultiMeldThirtyPlusPoints.txt");	
		Rummikub game5 =  new Rummikub("requirement4/playerWinFirstTurn.txt");	
		
		game1.play();
		game2.play();
		game3.play();
		game4.play();
		game5.play();
		
		int pointCount = 0;
		
		for(Meld m: game1.getBoard().getMeldsOnBoard()) {
			pointCount += m.pointValue();
		}
		
		assertTrue(pointCount == 30 && game1.getBoard().getMeldsOnBoard().size() == 1);
		
		pointCount = 0;
		
		for(Meld m: game2.getBoard().getMeldsOnBoard()) {
			pointCount += m.pointValue();
		}
		assertTrue(pointCount > 30 && game2.getBoard().getMeldsOnBoard().size() == 1);
		
		pointCount = 0;
		
		for(Meld m: game3.getBoard().getMeldsOnBoard()) {
			pointCount += m.pointValue();
		}
		assertTrue(pointCount == 30 && game3.getBoard().getMeldsOnBoard().size() > 1);
		
		pointCount = 0;
		
		for(Meld m: game4.getBoard().getMeldsOnBoard()) {
			pointCount += m.pointValue();
		}
		assertTrue(pointCount > 30 && game4.getBoard().getMeldsOnBoard().size() > 1);
		assertTrue(game5.getWinner() instanceof Human && game5.getTurnNumber() == 1);
		
	}
	
	public void testRequirementFive() throws IOException {
		Rummikub game;
		game = new Rummikub("requirement5/playerCanReuseMelds.txt");
		game.play();
		assertTrue(game.getBoard().getMeldsOnBoard().size() == 1);
		
		
	}
	public void testRequirementSix() throws IOException {
		Rummikub game;
		game = new Rummikub("requirement6/drawOneTest.txt");
		game.play();
	
		assertTrue(game.getPlayers().get(game.getTurnNumber()%4).getHand().getHandSize() == 15 );
	}
	public void testRequirementSeven() throws IOException {
		Rummikub game;
		game = new Rummikub("requirement7/consoleOutputTest.txt");
		game.play();
	
		assertTrue(game.allConsoleOutput.contains("Human Player played tiles"));
		assertTrue(game.allConsoleOutput.contains("Human Player's new hand:{R3,R4,R5,R6,R7,R8,R9,R10,R11,R12,R13}"));
		assertTrue(game.allConsoleOutput.contains("RUN {R1,R1,R2}"));
		assertTrue(game.allConsoleOutput.contains("*RUN {R1,R1,R2}"));
		
	}
	public void testRequirementEight() throws IOException {
		Rummikub game1 =  new Rummikub("requirement8/playerSingleRun.txt");
		Rummikub game2 =  new Rummikub("requirement8/playerSingleSet.txt");
		Rummikub game3 =  new Rummikub("requirement8/playerMultiRun.txt");
		Rummikub game4 =  new Rummikub("requirement8/playerMultiSet.txt");	
		Rummikub game5 =  new Rummikub("requirement8/playerSetAndRun.txt");	
		
		game1.play();
		game2.play();
		game3.play();
		game4.play();
		game5.play();
		
		
		assertTrue(game1.getBoard().getMeldsOnBoard().size() == 1 && game1.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN");
		assertTrue(game2.getBoard().getMeldsOnBoard().size() == 1 && game2.getBoard().getMeldsOnBoard().get(0).determineType() == "SET");
		assertTrue(game3.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN" && game3.getBoard().getMeldsOnBoard().get(1).determineType() == "RUN");
		assertTrue(game4.getBoard().getMeldsOnBoard().get(0).determineType() == "SET" && game4.getBoard().getMeldsOnBoard().get(1).determineType() == "SET");
		assertTrue(game5.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN" && game5.getBoard().getMeldsOnBoard().get(1).determineType() == "SET");
		
	}
	public void testRequirementNine() throws IOException {
		Rummikub game1 =  new Rummikub("requirement9/addToRunTest.txt");
		Rummikub game2 =  new Rummikub("requirement9/addToSetTest.txt");
		Rummikub game3 =  new Rummikub("requirement9/multiAddToRun.txt");
		Rummikub game4 =  new Rummikub("requirement9/usesExistingRunTile.txt");	
		Rummikub game5 =  new Rummikub("requirement9/usesExistingSetTile.txt");
		Rummikub game6 =  new Rummikub("requirement9/addToSeveralMelds.txt");
		Rummikub game7 =  new Rummikub("requirement9/largeBoardReorganize.txt");	
		
		
		game1.play();
		game2.play();
		game3.play();
		game4.play();
		game5.play();
		game6.play();
		game7.play();
		
		assertTrue(game1.getBoard().getMeldsOnBoard().get(0).size() ==  4 && game1.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN");
		
		assertTrue(game2.getBoard().getMeldsOnBoard().get(0).size() ==  4 && game2.getBoard().getMeldsOnBoard().get(0).determineType() == "SET");
		assertTrue(game3.getBoard().getMeldsOnBoard().get(0).size() ==  7 && game3.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN");
		assertTrue(game4.getBoard().getMeldsOnBoard().get(0).determineType() == "RUN" && game4.getBoard().getMeldsOnBoard().get(1).determineType() == "RUN");
		
		assertTrue(game5.getBoard().getMeldsOnBoard().get(0).determineType() == "SET" && game5.getBoard().getMeldsOnBoard().get(1).determineType() == "RUN");
		assertTrue(game6.getBoard().getMeldsOnBoard().get(0).determineType() == "SET" && game6.getBoard().getMeldsOnBoard().get(1).determineType() == "RUN");
		assertTrue(game6.getAllConsoleOutput().contains("SET {R10,B10,G10,O10}"));
		assertTrue(game6.getAllConsoleOutput().contains("RUN {R5,R6,R7,R8}"));
		
		
		
		for(Meld m: game7.getBoard().getMeldsOnBoard()){
			assertTrue(m.determineType() == "SET");
		}
		assertTrue(game7.getAllConsoleOutput().contains("SET {R7,B7,G7}\n" + "SET {R5,B5,G5}\n" + "SET {R6,B6,G6}"));
	
	}
	
	
	
	/*public void testConsole () {
		Rummikub game =  new Rummikub("baseCase.txt");
		game.play();
		
		//test for if deck deals all players 14
		AssetTrue(game.getPlayers().get(0).getStrategy() == "HUMAN");
		AssetTrue(game.getPlayers().get(1).getStrategy() == "1");
		AssetTrue(game.getPlayers().get(2).getStrategy() == "2");
		AssetTrue(game.getPlayers().get(3).getStrategy() == "3");
		
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
		LinkedList<String> commandQueue = new LinkedList<String>();
		
		
		
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
	*/
	
}
