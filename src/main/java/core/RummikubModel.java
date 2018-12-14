package core;
import java.util.concurrent.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Observer;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import junit.framework.Test;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class RummikubModel{
	
	private static final int MAX_CONSOLE_SIZE = 6;
	private static final int INITIAL_HAND_SIZE = 14;
	final Duration timeout = Duration.ofSeconds(10);
	ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private int state;
	private Board board;
	private Board referanceCopy = new Board();
	private ArrayList<Tile> deck =  new ArrayList<Tile>();
	private Player[] turnOrder;
	private Player currentPlayer;
	private LinkedList<Tile> initialDraws = new LinkedList<Tile>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private LinkedList<String> fileCommandQueue = new LinkedList<String>();
	private LinkedList<LinkedList<String>> playerTurnQueue = new LinkedList<LinkedList<String>>();
	private Player winner;
	private Tile newTile;

	private final Scanner input = new Scanner(System.in);
	
	private LinkedList<String> consoleOutput = new LinkedList<String>();
	
	private boolean fileMode;
	private boolean useTurnTimer;
	private boolean commandMode;
	private boolean needsToDraw = false;
	private boolean gameOver = false;
	
	private int maxTurns;
	private int playerCount;
	private int humanCount;
	final private long turnTimer = 10000000000L;
	
	private int turnNumber = 0;
	
	public String allConsoleOutput="";
	int roundCounter = 1;
	
	private Rummikub c; 
	private RummikubView v;
	
	public RummikubModel(Rummikub controller) {
		this.c = controller;
		this.v = new RummikubView(this);
		deck = freshDeck();
				
	}
	
	//Method that will handle all gameplay
	public void play() throws Exception{
		
		turnOrder = determineTurnOrder();
		currentPlayer = turnOrder[turnNumber%playerCount];
		state = 1;
		v.draw();
				
		for(Player p: players) {
			p.clearHand();
		}
		//Initial Deal
		commandHand(INITIAL_HAND_SIZE);
		
			state = 2;
			v.draw();
			
			final LinkedList<String> turnCommands = null;
 			turnNumber = 0;
			
			//Basic game loop
			do {
				currentPlayer = turnOrder[turnNumber%playerCount];
				
			
				needsToDraw = !(turnOrder[turnNumber%playerCount].play(getBoard(),deck,fileCommandQueue)); 
			     
				if(!needsToDraw) {
					state = 2;
					draw();
				}
				
				else{
					newTile = deck.remove(0); 
					turnOrder[turnNumber%playerCount].addToHand(newTile);
					state =4;
					draw();	
				}
		
				
				
				winCheck();
				turnNumber++;
				referanceCopy = new Board();
				
			}while(!gameOver && turnNumber < maxTurns);
			System.out.println(!gameOver && turnNumber > maxTurns);
			if (turnNumber > maxTurns)System.out.println("Exceeded max turns, selecting winnner");
			if (gameOver)System.out.println("0 cards");
			int  count =0;
			Player lowest = new Human();
			if(!gameOver) {
				do {
					if(lowest.playerName == null){lowest = turnOrder[count];}
					if(lowest.getHand().pointValue() > turnOrder[count].getHand().pointValue()) {lowest = turnOrder[count];}
				}while(++count<playerCount);
				winner = lowest;
			}
			state= 3;
			v.draw();
				
	}
	

	private void winCheck() {
		for(int i = 0; i<playerCount; i++){
			if(turnOrder[i].getHand().getHandSize() == 0) {
				winner = turnOrder[i];
				System.out.println(winner.toString() + "has no tiles left");
				gameOver = true;
			
			}
		}
	}
	public void commandHand(int numberOfCards) {
		if(commandMode) {
			for(Player  p: players) {
				for(int i = 0; i<numberOfCards; i++) {
					Boolean found = false;
					int itemIndex = 0;
					addConsoleOutput("Enter your Tiles one at a time ex \"G3\" "+p.getPlayerName() +" "+ (14 -p.getHand().getHandSize()) + " tiles remain");
					v.draw();
					Tile commandTile = new Tile(getInput());
					System.out.println(deck.toString());
					
					
					

					for(Tile t: deck) {
						if(commandTile.equals(t)) {
							itemIndex = deck.indexOf(t);
							found = true;
							break;
						}
					}
					
					if(!found) {
						
						addConsoleOutput("The deck does not contain that tile.");
						v.draw();
						i--;
					}else {
						p.hand.addTile(deck.remove(itemIndex));
					}
					
				}
			}
		}
		else {
			for(Player p: players) {
				for(int i=0; i<numberOfCards; i++) {
					p.addToHand(deck.remove(0));
				}
			}
		}
		return;
	}
	public Player[] determineTurnOrder(){
		
		
		
		if(commandMode){
			commandHand(1);
		}
		else{
			Boolean failed = false;
			Player  highest;
			
			do {
				failed = false;
				ArrayList<Tile> deckCopy = freshDeck();

				for(Player p: players) {
					p.addToHand(deckCopy.remove(0));
				}
				
			
				highest = players.get(0);
				for(Player p: players) {
					if(highest == null) {highest = p;}
					if(p.hand.pointValue() > highest.hand.pointValue())highest = p;
				}
				for(Player p: players) {
					if(p.hand.pointValue() == highest.hand.pointValue() && !p.getPlayerName().equals(highest.getPlayerName())) {
						failed = true;
			
					}
				}
				}while(failed);
			}
		Player highest;
		
		highest = players.get(0);
		for(Player p: players) {
			if(highest == null) {highest = p;}
			if(p.hand.pointValue() > highest.hand.pointValue())highest = p;
		}
		players.remove(highest);
		players.add(0,highest);
		return players.toArray(new Player[players.size()]);
			
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public int getMaxTurns() {
		return maxTurns;
	}
	public void setMaxTurns(int maxTurns) {
		this.maxTurns = maxTurns;
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
	public void screenBlank() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
	public boolean getCommandMode() {
		return commandMode;
	}
	public void setCommandMode(boolean commandMode) {
		this.commandMode = commandMode;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	public void setHumanCount(int humanCount) {
		this.humanCount = humanCount;
	}
	public int getHumanCount() {
		return humanCount;
	}
	
	
	public boolean usingTurnTimer() {
		return useTurnTimer;
	}
	public void setUseTurnTimer(boolean useTurnTimer) {
		this.useTurnTimer = useTurnTimer;
	}
	public boolean isFileMode() {
		return fileMode;
	}
	public void setFileMode(boolean fileMode) {
		this.fileMode = fileMode;
	}
	public void draw() {
		v.draw();
	}



	public void setBoard(Board board) {
		this.board = board;
	}



	public LinkedList<String> getFileCommandQueue() {
		return fileCommandQueue;
	}



	public void setFileCommandQueue(LinkedList<String> fileCommandQueue) {
		this.fileCommandQueue = fileCommandQueue;
	}
	


	public LinkedList<Tile> getInitialDraws() {
		return initialDraws;
	}



	public void setInitialDraws(LinkedList<Tile> initialDraws) {
		this.initialDraws = initialDraws;
	}
	
	public String  getInput(){
		
		String responce = null;
		
		while(responce == null) {
			try {
				responce = input.next();
				responce = responce.toUpperCase();
			}catch(Exception e) {
				System.out.println("There was an error in the input, please try again");
				responce = getInput();
			}
		}
		
		return responce;
	}


	public LinkedList<LinkedList<String>> getPlayerTurnQueue() {
		return playerTurnQueue;
	}



	public void setPlayerTurnQueue(LinkedList<LinkedList<String>> playerTurnQueue) {
		this.playerTurnQueue = playerTurnQueue;
	}

	public void addFileDeck(ArrayList<Tile> t, String fileString) {
		String[] tileList = fileString.split(" ");
		
		for(String s: tileList) {
			deck.add(new Tile(s));
		}
		
	}
	
	//Creates New Deck and add 106 empty tiles.
	public ArrayList<Tile> freshDeck(){
		ArrayList<Tile> t = new ArrayList<Tile>();
		
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "R");
			t.add(tile);
			t.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "B");
			t.add(tile);
			t.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "G");
			t.add(tile);
			t.add(tile);
		}
		for(int i = 1; i < 14; i++) {
			Tile tile = new Tile(i, "O");
			t.add(tile);
			t.add(tile);
		}
		Collections.shuffle(t);
		return t;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public Player[] getTurnOrder() {
		return turnOrder;
	}


	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		
		this.state = state;
	}
	

	public ArrayList<Tile> getDeck() {
		return deck;	
	}
	public void setDeck(ArrayList<Tile> tiles) {
		this.deck = tiles;
		return;
	}

	public Tile getNewTile() {
		return newTile;
	}

	public void setNewTile(Tile newTile) {
		this.newTile = newTile;
	}

	public Board getReferanceCopy() {
		return referanceCopy;
	}

	public LinkedList<String> getConsoleOutput() {
		return consoleOutput;
	}

	public void setConsoleOutput(LinkedList<String> consoleOutput) {
		this.consoleOutput = consoleOutput;
	}
	public void addConsoleOutput(String consoleOutputLine) {
		this.consoleOutput.addLast(consoleOutputLine);
		
		while(this.consoleOutput.size() > MAX_CONSOLE_SIZE) {
			this.consoleOutput.removeFirst();
		}
	}
	public RummikubView getRummikubView() {
		return v;
	}
	
		
}


