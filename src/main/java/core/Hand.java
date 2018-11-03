package core;

import java.util.ArrayList;

public class Hand {
	ArrayList<Tile> hand;
	
	public Hand() {
		hand = new ArrayList<Tile>();
		
	}
	
	public boolean addTile(Tile tile) {
		hand.add(tile);
		return true;
	}
	
	public boolean removeTile(Tile tile) {
		hand.remove(hand.indexOf(tile));
		return true;
	}
	
	public String printHand() {
		String string = "";
		for(int x = 0; x < hand.size(); x++) {
			string += hand.get(x).toString() ;
		}
		return string;
	}
	
	
	//Arrange the hand in order. First by colour then by number. 
	//Example hand = ["O2", "G9", "G2", "R11", "B2", "R2"] = ["R2", "R11", "B2", "G2", "G9", "O2"] 
	public ArrayList<Tile> arrangeHand(){
		return null;
		
		
	}

}
