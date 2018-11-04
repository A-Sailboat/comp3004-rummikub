package core;

import java.util.ArrayList;

public class Hand {
	ArrayList<Tile> hand;
	
	public Hand() {
		hand = new ArrayList<Tile>();
		
	}
	
	public void setHand(ArrayList<Tile> handToBeSet) {
		hand = handToBeSet;
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
			string += hand.get(x).printTile() + ", " ;
		}
		return string;
	}
	
	
	//Arrange the hand in order. First by colour then by number. 
	//Example hand = ["O2", "G9", "G2", "R11", "B2", "R2"] = ["R2", "R11", "B2", "G2", "G9", "O2"] 
	public void arrangeHand(){
		int lowestValueAlreadySorted = 49;
		int positionOfLowestValueAlreadySorted = 0;
		ArrayList<Tile> arrangedHand = new ArrayList<Tile>();
		
		//Goes through each element of hand and finds the one with the lowest value and removes from hand and adds to arrangedHand which will be set to hand.  
		while(hand.size() != 0) {
			lowestValueAlreadySorted = 49;
			for(int i = 0; i < hand.size(); i++) {
				
				if(hand.get(i).getValue() < lowestValueAlreadySorted) {
					lowestValueAlreadySorted = hand.get(i).getValue();
					positionOfLowestValueAlreadySorted = i;
				}
				
			}
			System.out.println(hand.get(positionOfLowestValueAlreadySorted).getValue());
			arrangedHand.add(hand.remove(positionOfLowestValueAlreadySorted));
		}
		
		//Sets hand to arranged hand;
		setHand(arrangedHand);
		
	}

}
