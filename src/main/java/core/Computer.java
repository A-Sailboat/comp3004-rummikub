package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Computer extends Player{
	
	public Computer(Ai newAiType) {
		aiType = newAiType;
	}

	
	@Override
	public String toString() {
		return "A Computer player";
	}

	@Override
	public String play(Board board, Deck deck, ArrayList<String> fileCommands) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
