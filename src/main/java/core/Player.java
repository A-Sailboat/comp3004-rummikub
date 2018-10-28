package core;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {
	ArrayList<Tile> hand = new ArrayList<Tile>();
	
	public Player() {}
	public Player(String arg[]) {}
	
	public Player(ArrayList<Tile> hand) {
		this.hand = hand;
	}
	
	
	public ArrayList<Tile> getHand() {
	return hand;
	}
	public abstract String play(Board board, Deck deck, ArrayList<String> fileCommands) {}
	

}
