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
	private Deck deck = new Deck();
	private ArrayList<Player> players = new ArrayList<Player>();
	private LinkedList<String> fileCommands = new LinkedList<String>();
	private final Scanner reader = new Scanner(System.in);
	private int turnNumber = 0;
	public Rummikub() {}
	public Rummikub(LinkedList fileCommands) {
	
		this.fileCommands = fileCommands
		
	}
	public void setDeck(Deck deck) {this.deck = deck;}
	public void setfileCommands(LinkedList<String> fileCommands){this.fileCommands = fileCommands;}
	public Deck getDeck() {return this.deck;}
	
	public Player play() {
		
		players.add(new Human(deck.deal()));
		players.add(new Computer(deck.deal(),1));
		players.add(new Computer(deck.deal(),2));
		players.add(new Computer(deck.deal(),3));
		
		turnNumber = 0;
		boolean gameOver = false;
		String selection;
		for(Player p: players) {
			p.hand()
		}
		
		while(!gameOver) {
			if(!fileCommands.isEmpty() && players.get(turnNumber%4)instanceof Human) {
				selection = fileCommands.poll();
			}else {
				selection = players.get(turnNumber%4).play(board,deck);
			}
			resolveTurn(players.get(turnNumber%4),board,deck,selection);
			}
		
		System.out.println("See you space cowboy...");
	}
	
	public void resolveTurn(Player p, Board b, Deck d, String selection) {
		
	}
	public String toString() {
		
		String  returnString = new String();
		
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += "\t"
		}
		returnString += "|\nl";
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += "\t"
		}
		returnString += "V\nl";
		
		for(int q = 0; q < (turnNumber % 4); q++) {
			returnString += ("Player " + q);
			if (players.get(turnNumber)%4 == 0) {returnString += "(Human)";}
			else {returnString += "Computer";}
			returnString += "\t";
		}
		returnString += "nl";
		returnString += board.toString()
	}

	
	public static void main(String arg[]) {
			try {
				
				if (arg.length == 0) {
					Rummikub game = new Rummikub();
					game.play();
				}
				if (arg.length == 1) { 
					
					File file = new File(fileLocation);
					Scanner sc = new Scanner(file);
					String commandLine =  sc.nextLine();
						
					
					String[] commands = commandLine.split("\n");
					System.out.println(commands);
						
					this.deck = new Deck(commands[0]);
						
					for(int i = 1; i<commands.length; i++) {
						fileCommands.offer(commands[i]);
					}
					Rummikub game = new Rummikub(fileCommands);
					game.play();
				} else {
					System.out.println("Correct usage is with 0 or 1 arguments (argument is the file name");
				}
				
			 
			} catch (Exception e) {
				System.err.println(e);
				System.exit(-1);
			}
		}
	
		
}

