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
import java.util.LinkedList;
import java.util.NoSuchElementException;
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


public class RummikubModel {
	
	final Duration timeout = Duration.ofSeconds(10);
	ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private int state;
	private Board board;
	private Board referanceCopy = new Board();
	private Deck deck;
	private Player[] turnOrder;
	private Player currentPlayer;
	private LinkedList<Tile> initialDraws = new LinkedList<Tile>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private LinkedList<String> fileCommandQueue = new LinkedList<String>();
	private LinkedList<LinkedList<String>> playerTurnQueue = new LinkedList<LinkedList<String>>();
	private Player winner;
	private Tile newTile;
	
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
	

	RummikubView	v = new RummikubView();
	
	public RummikubModel() {
		
	}
	
	//Method that will handle all gameplay
	public void play() throws Exception{
		
			//Determine Turn Order
			boolean failed = false;
			Player highest;
			turnOrder = new Player[getPlayerCount()];
			
				
			//System.out.println(turnOrder.length);
			if(fileMode) {
				for(Player p: players) {
					p.addToHand(getInitialDraws().poll());
				}
			}else if(commandMode){
				for(Player  p: players) {
					System.out.println("Enter your Tiles one at a time ex \"G3\" "+p.getPlayerName() + (14 -p.getHand().getHandSize()) + " tiles remain");
					p.hand.addTile(deck.dealTile(new Tile(getInput())));
				}
			}
			
			
			
			else{
				
				do {
				failed = false;
				Deck deckCopy = new Deck();
				deckCopy.shuffle();
				for(Player p: players) {
					p.addToHand(deckCopy.dealTile());
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
				highest = players.get(0);
				for(Player p: players) {
					if(highest == null) {highest = p;}
					if(p.hand.pointValue() > highest.hand.pointValue())highest = p;
				}
				players.remove(highest);
				players.add(0,highest);
				turnOrder  = players.toArray(new Player[players.size()]);
				
				for(int i = 0; i<playerCount; i++) {
					
				}
				currentPlayer = turnOrder[turnNumber%playerCount];
				state = 1;
				v.draw(this);
				
			
			
			//Initial Deal
			for(Player p: players) {
				for(int i=0; i<14; i++) {
					p.addToHand(deck.dealTile());
				}
			}
			state = 5;
			v.draw(this);
			
			final LinkedList<String> turnCommands = null;
 			turnNumber = 0;
			
			//Basic game loop
			do {
				currentPlayer = turnOrder[turnNumber%playerCount];
				
			
				needsToDraw = !(turnOrder[turnNumber%playerCount].play(getBoard(),deck,turnCommands)); 
			     
				if(!needsToDraw) {
					state = 7;
					v.draw(this);
				}
				
				else{
					newTile = deck.dealTile(); 
					turnOrder[turnNumber%playerCount].addToHand(newTile);
					state =4;
					v.draw(this);	
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
			state= 8;
			v.draw(this);
				
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

	public void setDeck(Deck deck) {
		this.deck = deck;
		
	}
	

	public Deck getDeck() {
		return  deck;	}

	public Tile getNewTile() {
		return newTile;
	}

	public void setNewTile(Tile newTile) {
		this.newTile = newTile;
	}

	public Board getReferanceCopy() {
		return referanceCopy;
	}
	
		
}


