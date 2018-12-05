package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Computer extends Player{
	
	public Computer(Ai newAiType) {
	
	}
	public Computer(int type,String name) {
		if(type == 0){this.aiType = new NoAi();}
		else if(type == 1) {this.aiType = new OneAi();}
		else if(type == 2) {this.aiType = new TwoAi();}
		else if(type == 3) {this.aiType = new ThreeAi();}
		this.playerName = name;
	}
	
	public void setAiType(int type) {
		if(type == 0){this.aiType = new NoAi();}
		else if(type == 1) {this.aiType = new OneAi();}
		else if(type == 2) {this.aiType = new TwoAi();}
		else if(type == 3) {this.aiType = new ThreeAi();}
	}
	
	
	
	@Override
	public String toString() {
		return "Computer Player "+playerName+"("+aiType.toString()+")";
	}
	
	public boolean play(Board board, Deck deck, Scanner reader) {
		return aiType.makePlay(board,deck,hand,playedThirtyPoints);
	}


	@Override
	public boolean play(Board board, Deck deck, LinkedList<String> commandQueue) {
		return aiType.makePlay(board,deck,hand,playedThirtyPoints);
	}
	

	
	

}
