package core;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import junit.framework.Test;
import java.util.Timer;

public class Rummikub{
	private BufferedReader reader;
	private FileReader fileReader;
	private final Scanner input = new Scanner(System.in);
	private String[] aiNames = {"GLaDOS","HAL-9000","DEEP BLUE","R2D2","JARVIS","DEEP THOUGHT","MARVIN","EDDIE","HACTAR"};
	
	RummikubModel r = new RummikubModel(this);
	RummikubView	v = r.getRummikubView();

	
	public Rummikub() {
		r.setFileMode(false);
		
		System.out.println("Hello and welcome to Rummikub.");
		System.out.println("Will you setup custom rules(Y/N).");
		
		if(getChoice()) {
			System.out.println("Would you like to enable Command Mode? (Choose Hand content, control your draws, ect)(Y/N)");
			r.setCommandMode(getChoice());
			
			System.out.println("Set Maximum Turns?");
			r.setMaxTurns(getInt());
			
			System.out.println("Turn on the 2 minute turn timer?");
			r.setUseTurnTimer(getChoice());
			
			System.out.println("How many players (2,3,4)?");
			r.setPlayerCount(getInt(2,4));
			
			System.out.println("How many human players?(0,1,2,3,4)");
			r.setHumanCount(getInt(0,4));
			
			for(int i = r.getHumanCount(); i>0; i--) {
				Human human = new Human();
				
				System.out.println("Enter a name for Human Player "+i);
				human.setPlayerName(getInput());
				
				r.getPlayers().add(human);
			}
			
			for(int i = r.getPlayerCount()-r.getHumanCount(); i>0; i--) {
				
				Computer comp = new Computer(new NoAi());
				comp.setPlayerName(aiNames[i]);
				
				System.out.println("Which AI should AI player "+ comp.getPlayerName() + " use?(1,2,3)");
				comp.setAiType(getInt(1,3));
				r.getPlayers().add(comp);
			}
			
		}else{
			r.setCommandMode(false);
			r.setMaxTurns(999);
			r.setUseTurnTimer(false);
			r.setPlayerCount(4);
			r.setHumanCount(1);
			
			r.getPlayers().add(new Human("Human Player"));
			r.getPlayers().add(new Computer(1,aiNames[0]));
			r.getPlayers().add(new Computer(2,aiNames[1]));
			r.getPlayers().add(new Computer(3,aiNames[2]));
		}
		
		
		
		r.setBoard(new Board());
		
	
		
		
	}
	public Rummikub(String fileLocation) throws IOException{
		r.setFileMode(true);
		String basePath = new File("").getAbsolutePath();
		System.out.print(basePath);
		reader = new BufferedReader(new FileReader(basePath+"/src/test/java/core/testCases/"+fileLocation));
			
		for(String line = reader.readLine(); line != null; line = reader.readLine()) {
			r.getFileCommandQueue().offer(line);
		}
		for(String command: r.getFileCommandQueue()) {
			System.out.println(command);
		}
		
		String[] settings = r.getFileCommandQueue().poll().split(",");
		//System.out.println(settings[0]+settings[1]);
		r.setCommandMode(Boolean.parseBoolean(settings[1]));
		r.setMaxTurns(Integer.parseInt(settings[2]));
		r.setUseTurnTimer(Boolean.parseBoolean(settings[3]));
		r.setPlayerCount(Integer.parseInt(settings[4]));
		r.setHumanCount(Integer.parseInt(settings[5]));
			
			for(int i = 6; i< 6+r.getHumanCount(); i++) {
				Human player = new Human(settings[i]);
				r.getPlayers().add(player);
			}
			
			for(int i = 6+ r.getHumanCount(); i<6+r.getPlayerCount(); i++) {
				Computer comp = new Computer(Integer.parseInt(settings[i]),aiNames[i%9]);
				r.getPlayers().add(comp);
			}
			/*
			  The first line has settings the next five lines contain tile data, 
			  in 4 groups of 14 and one group of the remaining tiles.This is done 
			  to promote readability. They all are added to the deck and will be
			  dealt in order such that:
			  Line 0 = Settings
			  Line 1 = Player Hand
			  Line 2 = CPU 1 Hand
			  Line 3 = CPU 2 Hand
			  Line 4 = CPU 3 Hand
			  Line 5 = remainder of deck
			*/
			
			String deckString = "";
			for(int i = 0; i<5; i++) {
				deckString += r.getFileCommandQueue().poll();
			}
			
			r.setDeck(addFileDeck(deckString.trim()));
			
			for(String s: r.getFileCommandQueue().poll().split(",")) {
				System.out.println("%"+s);
				r.getInitialDraws().offer(new Tile(s));
			}
			
			//The remaining lines contain commands that will occur on the player's turns
			r.setBoard(new Board());
			
			
	}
	
	//Method that will handle all gameplay
	

	public String  getInput(ArrayList<String> acceptableResponces){
		if(r.getFileCommandQueue().isEmpty()){
		String responce = null;
		
			do {
				try {
					responce = input.next();
					responce = responce.toUpperCase();
				}catch(Exception e) {
					System.out.println("There was an error in the input, please try again");
					System.out.println("Acceptable responces are: "+acceptableResponces);
					responce = getInput(acceptableResponces);
				}
			}while(!acceptableResponces.contains(responce));
		
		
		return responce;
		}else {
			return r.getFileCommandQueue().poll();
		}
	}
	
	public boolean getChoice(){
	
		String response = null;
		if(r.getFileCommandQueue().isEmpty()){
			do{
			try {
				response = input.next();
				response = response.toUpperCase();
			//	System.out.println("gottem");
			
			}catch(Exception e) {
				System.out.println("There was an error in the input, please try again");
				e.printStackTrace();
			}
			//System.out.println("loopy");
			//System.out.println(!(response.equals("Y")) && !(response.equals("N")));
			}
			while(!(response.equals("Y") || response.equals("N")));
		}else {
			response = r.getFileCommandQueue().poll();
		}
		
		//System.out.println(response);
		
		if(response.equals("Y")) {return true;}
		if(response.equals("N")) {return false;}
		return false;
		
	}
	public int getInt(){
		boolean notDone = true;
		int returnInt = 0;
		
		if(r.getFileCommandQueue().isEmpty()){
			
			do {
				try {
					returnInt = Integer.parseInt(input.next());
					notDone = false;
				}catch(Exception e) {
					System.out.println("There was an error in the input, please try again");
					returnInt = getInt();
				}
		
			}while(notDone);
		}else {returnInt = Integer.parseInt(r.getFileCommandQueue().poll());}
			return returnInt;
		
	}
	public int getInt(int min, int max){
	
		int returnInt = 0;
		if(r.getFileCommandQueue().isEmpty()){
		do {
			try {
				returnInt = Integer.parseInt(input.next());
			}catch(Exception e) {
				System.out.println("There was an error in the input, please try again");
				returnInt = getInt(min,max);
			}
		}while(returnInt < min ||returnInt > max);
		}else {returnInt = Integer.parseInt(r.getFileCommandQueue().poll());}
		
		return returnInt;
	}
	public ArrayList<Tile> addFileDeck(String fileString) {
		ArrayList<Tile> t = new ArrayList<Tile>();
		String[] tileList = fileString.split(" ");
		
		for(String s: tileList) {
			t.add(new Tile(s));
		}
		return t;
	}
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
	public String  getInput(){
		
		String responce = null;
		if(r.getFileCommandQueue().isEmpty()){
			while(responce == null) {
				try {
					responce = input.next();
					responce = responce.toUpperCase();
				}catch(Exception e) {
					System.out.println("There was an error in the input, please try again");
					responce = getInput();
				}
			}
		}else{responce = r.getFileCommandQueue().poll();}
		return responce;
	}
	

	
	public static void main(String[] args) {
	
			try {

				if (args.length == 0) {
					Rummikub game = new Rummikub();
					game.r.play();
				}
				else if (args.length == 1) { 
					Rummikub game = new Rummikub(args[0]);
					game.r.play();
				}else {
					System.out.println("Correct usage is with 0 or 1 arguments (argument is the file name");
				}
				
			 
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
	}

		
}

