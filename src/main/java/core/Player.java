package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Player {
	Hand hand = new Hand();
	String playerName;
	boolean playedThirtyPoints = false;
	
	public Ai aiType;
	
	
	public Hand getHand() {
		return hand;
	}
	
	public Hand addToHand(Tile tile) {
		hand.add(tile);
		return hand;
	}
	
	public abstract boolean play(Board board, Deck deck, LinkedList<String> commandQueue) ;
	
	public String printAiType(){
		
		return aiType.toString();
		
	}
	
	public void setAiType(Ai newAiType){
		
		aiType = newAiType;
		
	}


	
	
	public void setPlayerName(String setName) {
		playerName = setName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Ai getAiType() {
		return aiType;

	}
}
