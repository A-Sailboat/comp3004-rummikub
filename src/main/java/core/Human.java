package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Human extends Player{

	public Human() {
		aiType = new NoAi();
	}
	public Human(String name) {
		aiType = new NoAi();
		this.playerName = name;
	}

	@Override
	public String toString() {
		return "Human Player "+ playerName;
	}
	@Override
	public boolean play(Board board, ArrayList<Tile> deck, LinkedList<String> commandQueue) {
		boolean madeAPlay = false;
		final Scanner lines = new Scanner(System.in);
		System.out.print(commandQueue);
		Board backupBoard = board;
		Hand backupHand = hand;
		
		Meld focusedMeld = new Meld();
		Meld bufferMeld = new Meld();
		
		String response = "";
		
		
		String selectInstructions = ("What would you like to do (A)dd a new Meld, (M)odify an Existing Meld, (E)nd Turn\n");
		
		String addInstructions	=	("Select the  tile you want to add 0,1,2,3...ect numbers assigned left to right or,(F)inish modifiying this meld\n");
		
		String modifyInstructions = ("Select the meld you'd like to alter, 0,1,2,3...ect or (F)inish modifying (N)to commit the buffer as a meld \n");
		String modifyInstructions2 = ("Move a tile from the meld to the buffer with \"M\" followed by its number, 0,1,2,3...ect\n"
									+ "Move a tile to the buffer from your hand with \"H\" followed by its number 0,1,2,3... ect\n"
									+ "Move a tile from the buffer to a meld with \"B\" followed by its number  0,1,2,3...ect or (S) to select a new  meld or finish\n");
	
		
	
		
		
		while(!response.toUpperCase().equals("E")) {
			
			System.out.print(selectInstructions);
			//if(!playedThirtyPoints) {System.out.print("You can't modify melds until you've played 30 points of melds.");} 
			System.out.print("Board:\n"+board.toString());
			
			System.out.print("Your Hand: "+hand.toString()+"\n" );
			
			if(commandQueue.isEmpty() || commandQueue == null) {response = lines.nextLine();
			}else {response = commandQueue.poll();}
			
			if(!response.toUpperCase().equals("E")) {
				
				if(response.toUpperCase().equals("A")){
					while(!response.toUpperCase().equals("F") ) {
						System.out.print(addInstructions);
						System.out.print("New Meld: "+ bufferMeld.toString()+"\n");
						System.out.print("Your Hand: "+hand.toString()+"\n" );
						
						if(commandQueue == null || commandQueue.isEmpty()) {response = lines.nextLine();}
						else {response = commandQueue.poll();}
						
	
						try{
							bufferMeld.add(hand.remove(Integer.parseInt(response)));
							madeAPlay = true;
						}
						catch(NumberFormatException e){}
						
					}
					board.add(bufferMeld);
					bufferMeld  = new Meld();
				}else if(response.toUpperCase().toUpperCase().equals("M")){
					while(!response.toUpperCase().toUpperCase().equals("F")) {
						response = "";
						madeAPlay = true;
						System.out.print(modifyInstructions);
						System.out.print("Board:\n"+board.toString());
						if(commandQueue == null || commandQueue.isEmpty()) {
							String hold = lines.nextLine();
							if (hold.toUpperCase().equals("F")||hold.toUpperCase().equals("N")) {
								response = hold;
							}else {
								focusedMeld = board.remove(Integer.parseInt(hold));
							}
							
						}else {
							//System.out.print(commandQueue);
							if(commandQueue.peekFirst().toUpperCase().equals("F")||commandQueue.peekFirst().toUpperCase().equals("N")) {
								response = commandQueue.poll();
							}else {
							
							focusedMeld = board.remove(Integer.parseInt(commandQueue.poll()));
							}
						}
						
						while(!response.toUpperCase().equals("S") && !response.toUpperCase().equals("F") && !response.toUpperCase().equals("N")){
							System.out.print(modifyInstructions2);
							System.out.print("Seleted Meld:" + focusedMeld.toString()+"\n");
							System.out.print("Buffer Meld: "+ bufferMeld.toString()+"\n");
							System.out.print("Your Hand: "+hand.toString()+"\n" );
			
							if(commandQueue == null || commandQueue.isEmpty()) {response = lines.nextLine();}
							else {response = commandQueue.poll();}
							
							try{
								if (response.charAt(0) == 'B'){
									focusedMeld.add(bufferMeld.remove(Integer.parseInt(response.substring(1))));
									
								}else if(response.charAt(0) == 'H') {
									bufferMeld.add(hand.remove(Integer.parseInt(response.substring(1))));
									
								}else if(response.charAt(0) =='M') {
									bufferMeld.add(focusedMeld.remove(Integer.parseInt(response.substring(1))));
								}
								
							}
							catch(NumberFormatException e){
								e.printStackTrace();
								System.out.print("An error occured, try again");
							}
							System.out.print(response);
						}
						if(response.equals("N") && !bufferMeld.getMeld().isEmpty()) {
							board.add(bufferMeld);
							bufferMeld = new Meld();
						}
						if(!response.equals("F") && !focusedMeld.getMeld().isEmpty()) {
						board.add(focusedMeld);
						focusedMeld  = new Meld();
						}
						if (!bufferMeld.isEmpty() && response.equals("F")){
							System.out.print("You need to empty the buffer meld before your turn can end.");
							response = "";
						}
					}
				}	
	
				
			}
		}

		return madeAPlay;
	}
	


}


