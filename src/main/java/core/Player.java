package core;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {
	Hand hand = new Hand();
	
	public Ai aiType;
	
	
	public Hand getHand() {
		return hand;
	}
	
	public Hand addToHand(Tile tile) {
		hand.addTile(tile);
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
