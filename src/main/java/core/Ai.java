package core;

import java.util.LinkedList;

public interface Ai {
	
	abstract String toString();

	abstract boolean makePlay(Board board, Deck deck, Hand hand, boolean playedThirtyPoints);

}

class NoAi implements Ai{
	public String toString() {
		return "No ai";
	}

	public boolean makePlay(Board board, Deck deck, Hand hand,boolean playedThirtyPoints) {
		// TODO Auto-generated method stub
		return false;
	}
}

//Each Ai will decide differently how to play it's tiles. 

//Represents Ai Strategy 1
class OneAi implements Ai{
	public String toString() {
		return "AI strategy 1";
	}
	
	public Tile canPlayOnMeld(Meld meld) {
		return null;
	}
	public boolean makePlay(Board board, Deck deck, Hand hand,boolean playedThirtyPoints) {
		int pointCount;
		
		//f(!playedThirtyPoints) {
			
		//}
		return false;
	}
}

//Represents Ai Strategy 2
//Do not have to implement for iteration one. 
class TwoAi implements Ai{
	public String toString() {
		return "AI strategy 2";
	}
	
	public Tile canPlayOnMeld() {
		return null;
	}

	public boolean makePlay(Board board, Deck deck, Hand hand, boolean playedThirtyPoints) {
		return false;
	}
	
}

//Represents Ai Strategy 3
class ThreeAi implements Ai{
	public String toString() {
		return "AI strategy 3";
	}
	
	public Tile canPlayOnMeld() {
		return null;
	}

	public boolean makePlay(Board board, Deck deck, Hand hand, boolean playedThirtyPoints) {
		
		return false;
	}
}

