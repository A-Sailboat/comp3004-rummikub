package core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Tile> deck = new ArrayList<Tile>();
	
	public Deck(String string) {
		String[] tileList= string.split(" ");
		
		for(String t: tileList) {
			deck.add(new Tile(t));
		}
		
	}
	
	//Creates New Deck and add 106 empty tiles.
	public Deck() {
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "R");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "B");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "G");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "O");
			deck.add(tile);
			deck.add(tile);
		}
		////Tile tile = new Tile(0, "J");
		//	deck.add(tile);
		//	deck.add(tile);
		/*while(size() != 106) {
			Tile tile = new Tile();
			deck.add(tile);
		}*/
	}
	
	//Shuffles current deck 
	public boolean shuffle(){
		Collections.shuffle(deck);
		return true;
	}
	
	//Returns tile at position 0 and removes said tile from the deck. 
	public Tile dealTile() {
		if(deck.size() > 0) {
			return deck.remove(0);
		}
		return null;
	}
	public Tile dealTile(Tile t) {
		
		if(deck.contains(t)) {return deck.remove(deck.indexOf(t));}
		return null;
	}
	public Boolean contains(Tile t) {
		return deck.contains(t);
	}
	
	
	//Returns the size of ArrayList deck.
	public int size() {
		return deck.size();
	}
	
	public String toString() {
		String returnString = "[";
		for(Tile t: deck) {
		returnString += t.getColor() + t.getNumber()+",";
		}
		returnString += "]";
		return returnString;
	}

}
