package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Rummikub {
	
	private Board board = new Board();
	private Deck deck;
	private ArrayList<Player> players = new ArrayList<Player>();
	private LinkedList<String> commandQueue = new LinkedList<String>();
	private final Scanner reader = new Scanner(System.in);
	private int maxTurns;
	private int turnNumber = 0;
	boolean gameOver = false;
	
	//Creates new Deck/Stock and adds a Human Player, and three Computer Players with different AI. 
	public Rummikub() {
		deck = new Deck();
		
		players.add(new Human());
		players.add(new Computer(new OneAi()));
		players.add(new Computer(new TwoAi()));
		players.add(new Computer(new ThreeAi()));
		
	}
	public Rummikub(String fileLocation) {
	
		try {
			File file = new File(fileLocation);
			Scanner sc = new Scanner(file);
			String commandLine =  sc.nextLine();
			String[] commands = commandLine.split("\n");
			
			/*The first five lines contain tile data, in 4 groups of 14 and one group of
			  the remaining tiles. This is done to promote readability. They all are added
			  to the deck and will be dealt in order such that:
			  Line 0 = Player Hand
			  Line 1 = CPU 1 Hand
			  Line 2 = CPU 2 Hand
			  Line 3 = CPU 3 Hand
			  Line 4 = remainder of deck
			*/
			
			this.deck = new Deck(commands[0]+commands[1]+commands[2]+commands[3]+commands[4]);
		
			//Line 5 contains a number that represents maxTurns before game ends,
			//useful for testing
			maxTurns = Integer.parseInt(commands[5]);
			
			//The remaining lines contain commands that will occur on the player's turns
			for(int i = 7; i<commands.length; i++) {
				commandQueue.offer(commands[i]);
			}
			
			players.add(new Human());
			players.add(new Computer(new OneAi()));
			players.add(new Computer(new TwoAi()));
			players.add(new Computer(new ThreeAi()));
			
		}catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
		
	}
	
	
	public void setDeck(Deck deck) {this.deck = deck;}
	//public void setCommandQueue(LinkedList<String> commandQueue){this.commandQueue = fileCommands;}
	public Deck getDeck() {return this.deck;}
	
	public void play() {
		try {
			
			//Give each player 14 tiles
			for(Player p: players) {
				for(int i=0; i<14; i++) {
					p.getHand().add(deck.dealTile());
				}
			}
			
			String selection;
			turnNumber = 1;
			while(!gameOver && turnNumber < maxTurns) {
				
				//If commands from the file are present and it is the players turn it will
				//get commands rather than get player input
				if(!commandQueue.isEmpty() && players.get(turnNumber%4)instanceof Human) {
					selection = commandQueue.poll();
				}else {
					selection = players.get(turnNumber%4).play(board,deck);
				}
				resolveTurn(players.get(turnNumber%4),board,deck,selection);
				turnNumber++;
			}
		
			System.out.println("See you space cowboy...");
			
			System.out.println("Play is Ready");
		}catch(Exception e){
			System.out.println("Error Dealing Cards");
		}
		
	}
	
	public void resolveTurn(Player p, Board b, Deck d, String selection) {
		
	}
	/*public String toString() {
		
		String  returnString = new String();
		
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += "\t";
		}
		returnString += "|\nl";
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += "\t";
		}
		returnString += "V\nl";
		
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += ("Player " + q);
			//DON'T KNOW WHAT IS SUPPOSED TO HAPPEN HERE - Cooper 
			if (players.get(turnNumber) % 4 == 0) {returnString += "(Human)";}
			else {returnString += "Computer";}
			returnString += "\t";
		}
		returnString += "nl";
		returnString += board.toString();
	}*/

	
	public static void main(String[] args) {
			try {
				System.out.println(args.length);
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
				System.err.println(e);
				System.exit(-1);
			}
		}
	
		
}

