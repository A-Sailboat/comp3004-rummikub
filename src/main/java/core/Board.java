package core;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Meld> melds = new ArrayList<Meld>();
	boolean complete = false;
	Deck boardDeck = new Deck();
	Meld get(int i){return melds.get(i);}
	Meld remove(int i){return melds.remove(i);}
	
	public Board() {
		boardDeck.shuffle();
	}
	
	public Tile dealFromBoardDeck() {
		return boardDeck.dealTile();
	}
	
	public ArrayList<Meld> getMeldsOnBoard() {
		return melds;
	}
	
	
	public String printBoard(){
		String returnString = new String();
		
		int longestMeld = 0;
		for(Meld m: melds) {
			if (m.size() > longestMeld)longestMeld = m.size();
		}
		returnString += "\t\t";
		for(Integer i = 0; i<longestMeld; i++) {
			returnString += "\t"+ i.toString();
		}
		returnString += "\nl";
		
		for(Integer q = 0; q<melds.size(); q++) {
			returnString += q.toString()+ "\t" + melds.get(q).toString() + "\nl";
			
		}
		return returnString;
	}
	
}

