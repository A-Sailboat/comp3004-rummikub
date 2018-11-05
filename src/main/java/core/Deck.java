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
		while(size() != 106) {
			Tile tile = new Tile();
			deck.add(tile);
		}
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
	
	//Returns the size of ArrayList deck.
	public int size() {
		return deck.size();
	}

}
