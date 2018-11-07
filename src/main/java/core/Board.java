package core;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Meld> melds = new ArrayList<Meld>();
	Deck boardDeck = new Deck();
	Meld get(int i){return melds.get(i);}
	Meld remove(int i){return melds.remove(i);}
	

	public void add(Meld m) {melds.add(m);}
	
	public Board() {
		boardDeck.shuffle();
	}
	
	public Board(Board board) {
		this.melds = board.melds;
	}
	public Tile dealFromBoardDeck() {
		return boardDeck.dealTile();
	}
	
	public ArrayList<Meld> getMeldsOnBoard() {
		return melds;
	}
	
	
	public String toString(){

		String returnString = new String();
		
		for(Meld m: melds) {
			returnString += m.toString()+"\n";
			
		}
		/*
		int longestMeld = 0;
		for(Meld m: melds) {
			if (m.size() > longestMeld)longestMeld = m.size();
		}
		returnString += "\t\t";
		for(Integer i = 0; i<longestMeld; i++) {
			returnString += "\t"+ i.toString();
		}
		returnString += "\n";
		
		for(Integer q = 0; q<melds.size(); q++) {
			returnString += q.toString()+ "\t" + melds.get(q).toString() + "\n";
			
		}
		*/
		return returnString;
	}
	public String toString (Board previous) {
		System.out.print("$"+previous);
		String returnString = new String();
	
		for(Meld m: melds) {
			if(!(previous.melds.contains(m)))returnString += "*";
			returnString += m.toString()+"\n";
		
		}
		return returnString;
	}
}

