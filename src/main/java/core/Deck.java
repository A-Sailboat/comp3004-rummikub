package core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Tile> deck = new ArrayList<Tile>();
	
	public Deck(String string) {
		
	}
	public Deck() {
		while(size() != 106) {
			Tile tile = new Tile();
			deck.add(tile);
		}
	}
	
	public boolean shuffle(){
		Collections.shuffle(deck);
		return true;
	}
	
	public Tile dealTile() {
		if(deck.size() > 0) {
			return deck.remove(0);
		}
		return null;
	}
	
	public int size() {
		return deck.size();
	}

}
