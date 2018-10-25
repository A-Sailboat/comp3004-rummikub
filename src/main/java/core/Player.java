package core;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	ArrayList<Tile> hand = new ArrayList<Tile>();
	
	public Player() {}
	
	public Player(ArrayList<Tile> hand) {
		this.hand = hand;
	}
	
	
	public ArrayList<Tile> getHand() {
	return hand;
	}
	

}
