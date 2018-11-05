package core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Tile> deck = new ArrayList<Tile>();
	
	public Deck(String string) {
		
	}
	
	//Creates New Deck and add 106 empty tiles.
	public Deck() {
		for(int i = 0; i < 13; i++) {
			Tile tile = new Tile(i, "R");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 0; i < 13; i++) {
			Tile tile = new Tile(i, "B");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 0; i < 13; i++) {
			Tile tile = new Tile(i, "G");
			deck.add(tile);
			deck.add(tile);
		}
		for(int i = 0; i < 13; i++) {
			Tile tile = new Tile(i, "O");
			deck.add(tile);
			deck.add(tile);
		}
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
	
	//Returns the size of ArrayList deck.
	public int size() {
		return deck.size();
	}

}
