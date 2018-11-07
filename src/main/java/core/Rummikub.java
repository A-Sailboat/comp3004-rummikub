package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import junit.framework.Test;


public class Rummikub {
	private BufferedReader reader;
	private FileReader fileReader;
	private final Scanner input = new Scanner(System.in);
	private Board board;
	private Board referanceCopy = new Board();
	private Deck deck;
	private ArrayList<Player> players = new ArrayList<Player>();
	private LinkedList<String> fileCommandQueue = new LinkedList<String>();
	private LinkedList<LinkedList<String>> playerTurnQueue = new LinkedList<LinkedList<String>>();
	private Player winner;
	private int maxTurns = 99;
	private int turnNumber = 0;
	private boolean needsToDraw = false;
	public String allConsoleOutput="";
	int roundCounter = 1;
	boolean gameOver = false;
	
	
	public Rummikub() {
		deck = new Deck();
		deck.shuffle();
		
		board = new Board();
		
		players.add(new Human());
		players.add(new Computer(new OneAi()));
		players.add(new Computer(new OneAi()));
		players.add(new Computer(new ThreeAi()));
		
	}
	public Rummikub(String fileLocation) throws IOException{
	
			reader = new BufferedReader(new FileReader("/home/henry/eclipse-workspace/comp3004-rummikub/src/test/java/core/testCases/"+fileLocation));
			
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				fileCommandQueue.offer(line);
			}
	
		
			/*
			  The first five lines contain tile data, in 4 groups of 14 and one group of
			  the remaining tiles. This is done to promote readability. They all are added
			  to the deck and will be dealt in order such that:
			  Line 0 = Player Hand
			  Line 1 = CPU 1 Hand
			  Line 2 = CPU 2 Hand
			  Line 3 = CPU 3 Hand
			  Line 4 = remainder of deck
			*/
			
			String deckString = "";
			for(int i = 0; i<5; i++) {
				deckString += fileCommandQueue.poll();
			}
			this.deck = new Deck(deckString.trim());
			//Line 5 contains a number that represents maxTurns before game ends,
			//useful for testing
			
			maxTurns = Integer.parseInt(fileCommandQueue.poll());
			while(!fileCommandQueue.isEmpty()){
				LinkedList<String> aTurn = new LinkedList<String>();
				aTurn.offer(fileCommandQueue.poll());
				while(!aTurn.getLast().equals("E")){
					aTurn.offer(fileCommandQueue.poll());
				}
				playerTurnQueue.offer(aTurn);
			}
			
			//The remaining lines contain commands that will occur on the player's turns
			board = new Board();
			
			players.add(new Human());
			players.add(new Computer(new OneAi()));
			players.add(new Computer(new TwoAi()));
			players.add(new Computer(new ThreeAi()));
			
	}
	
	
	public void setDeck(Deck deck) {this.deck = deck;}
	public void setfilefileCommandQueue(LinkedList<String> filefileCommandQueue){this.fileCommandQueue = filefileCommandQueue;}
	public Deck getDeck() {return this.deck;}


	
	//Method that will handle all gameplay
	public void play() {
		
			//Initial Deal
			for(Player p: players) {
				for(int i=0; i<14; i++) {
					p.addToHand(deck.dealTile());
				}
			}
			
			System.out.println("Initial Hands: \n" );
			allConsoleOutput +="Initial Hands: \n";
			for (Player p: players) {
				p.getHand().arrangeHand();
				System.out.println("\t" + p.getHand().printHand());
				allConsoleOutput += "\t" + p.getHand().printHand();
			}
			
			
			
			
			LinkedList<String> turnCommands = null;
			turnNumber = 0;
			
			//Basic game loop
			while(!gameOver && turnNumber <= maxTurns) {
				
				System.out.print(" Player "+ (turnNumber%4+1)+ "'s Turn ");
				allConsoleOutput += " Player "+ (turnNumber%4+1)+ "'s Turn ";
				if(players.get(turnNumber%4)instanceof Human){
					System.out.print("(Human)\n");
					allConsoleOutput += "(Human)\n";
				}else {
					System.out.print("(Computer)\n");
					allConsoleOutput += "(Human)\n";
				}
				//If commands from the file are present and it is the players turn it will
				//get commands rather than get player input
				if(!playerTurnQueue.isEmpty() && players.get(turnNumber%4)instanceof Human) {
					turnCommands = playerTurnQueue.poll();
				}
				for(Meld m: board.getMeldsOnBoard()) {
					referanceCopy.add(m);
				}
				needsToDraw = !(players.get(turnNumber%4).play(board,deck,turnCommands));
				
				if(!needsToDraw) {
					System.out.print(players.get(turnNumber%4).toString()+" played tiles\n"
									+ players.get(turnNumber%4).toString()+"'s new hand:"+players.get(turnNumber%4).getHand().toString()+"\n"
									+"The Updated Board:\n"+board.toString(referanceCopy));
					
					allConsoleOutput += players.get(turnNumber%4).toString()+" played tiles\n"
							+ players.get(turnNumber%4).toString()+"'s new hand:"+players.get(turnNumber%4).getHand().toString()+"\n"
							+"The Updated Board:\n"+board.toString(referanceCopy);
				}
				
				if (needsToDraw) {
					Tile aTile = deck.dealTile(); 
					System.out.print(players.get(turnNumber%4).toString() +" draws one tile "+ aTile.toString()+ "\n");
					allConsoleOutput += players.get(turnNumber%4).toString() +" draws one tile "+ aTile.toString()+ "\n";
					players.get(turnNumber%4).addToHand(aTile);
					
				}
		
				
				
				winCheck();
				turnNumber++;
				referanceCopy = new Board();
				
				
				for(int i = 0; i<1; i++){
					
					System.out.println("______________________________________________________________________________________________________");
					allConsoleOutput += "______________________________________________________________________________________________________";
				}
				
			
		
				
				
			}
		
			System.out.println("See you space cowboy...");
			allConsoleOutput += "See you space cowboy...";
	}
	

	private void winCheck() {
		for(int i = 0; i<4; i++){
			if(players.get(i).getHand().getHandSize() == 0) {
				System.out.println("Player "+ i+1+ " Wins");
				allConsoleOutput += "Player "+ i+1+ " Wins";
				gameOver = true;
				winner = players.get(i);
			}
		}
	}



		

	public String toString() {
		
		String  returnString = new String();
		
		
		returnString += (" Player "+ (turnNumber%4+1)+ "'s Turn ");
		
		if(players.get(turnNumber%4)instanceof Human){
			returnString += "(Human)\n";
		}else {
			returnString += "(Computer)\n";
		} 
		returnString+="Melds on Board:\n";
		returnString += board.toString();
		
		if(players.get(turnNumber%4)instanceof Human){
			returnString += "Your Hand:\n";
			returnString += players.get(turnNumber%4).getHand().toString()+"\n";
		}
		return returnString;
	}

	
	public static void main(String[] args) {
	
			try {

				if (args.length == 0) {
					Rummikub game = new Rummikub();
					game.play();
				}
				else if (args.length == 1) { 
					Rummikub game = new Rummikub(args[0]);
					game.play();
				}else {
					System.out.println("Correct usage is with 0 or 1 arguments (argument is the file name");
				}
				
			 
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public String getAllConsoleOutput() {
		return allConsoleOutput;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public int  getTurnNumber() {
		return turnNumber;
	}
	public Board getBoard() {
		return board;
	}
	
	
		
}

