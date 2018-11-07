package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Computer extends Player{
	
	public Computer(Ai newAiType) {
		aiType = newAiType;
	}

	
	@Override
	public String toString() {
		return "Computer Player ("+aiType.toString()+")";
	}
	
	public boolean play(Board board, Deck deck, Scanner reader) {
		return aiType.makePlay(board,deck,hand,playedThirtyPoints);
	}


	@Override
	public boolean play(Board board, Deck deck, LinkedList<String> commandQueue) {
		return aiType.makePlay(board,deck,hand,playedThirtyPoints);
	}
	

	
	

}
