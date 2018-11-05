package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human extends Player{
	
	public Human() {
		aiType = new NoAi();
	}

	@Override
	public String toString() {
		return "A human player";
	}
	@Override
	public String play(Board board, Deck deck, Scanner reader) {
		String returnString = "";
		String meldString	="";
		String response = "";
		System.out.print("What will you do?\nl"
						+ "Enter H0,H1,H2...ect to add a meld from your hand to the new meld\nl"
						+ "Enter M0,M1,M2 to return a tile from the new meld to your hand\nl"
						+ "Enter MELD to commit the  meld to the board\nl"
						+ "Enter a blank line to end turn");
		response = reader.nextLine();
		while(response != "") {
			
			while(response.toUpperCase() != "MELD" || response != "") {
				
				if(response.toUpperCase().charAt(0) == 'H')	{meldString 	+= " "+response;}
				else if(response.toUpperCase().charAt(0) == 'M') {meldString+= " "+response;}
				else if(response.toUpperCase() == "MELD") {
					meldString+= "/"+response;
					meldString = meldString.trim();
					returnString += meldString;
					
																						}
				else {
				System.out.print("Commnand Misrecognition");
				}
				response = reader.nextLine();
			}
			
		}
			
		return returnString;
		
	}

	@Override
	public String play(Board board, Deck deck) {
		// TODO Auto-generated method stub
		return null;
	}
}


