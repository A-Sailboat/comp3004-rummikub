package core;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {
	ArrayList<Tile> hand = new ArrayList<Tile>();
	
	public Ai aiType;
	
	public Player() {}
	
	public Player(ArrayList<Tile> hand) {
		this.hand = hand;
	}
	
	
	public ArrayList<Tile> getHand() {
		return hand;
	}
	
	public abstract String play(Board board, Deck deck);
	
	
	//Not actual implementation. Will need later. 
	public String printAiType(){
		
		return aiType.ai();
		
	}
	
	public void setAiType(Ai newAiType){
		
		aiType = newAiType;
		
	}
	
	public Ai getAiType() {
		return aiType;
	}
}
