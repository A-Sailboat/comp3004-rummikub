package core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meld  {
	
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private String type = new String();

	public Meld() {}
	
	public Meld(ArrayList<Tile> tiles ) {
		this.tiles = tiles;
		this.type  = this.determineType();
		}
	
	public List<Tile> getMeld() {return tiles;}
	
	public int pointValue() {
		int count = 0;
		for(Tile t: tiles) {
			count+= t.getNumber();
		}
		return count;
	}
	public int  size() {return tiles.size();}
	
	public void add(Tile aTile) {
		tiles.add(aTile);
		this.type = this.determineType();
		Collections.sort(tiles);
	}
	public void remove(Tile aTile) {
		tiles.remove(aTile);
		this.type = this.determineType();
		Collections.sort(tiles);
	}
	

	public Tile remove(int i) {
		Tile aTile = tiles.remove(i);
		this.type = this.determineType();
		Collections.sort(tiles);
		return aTile;
	}
	
	
	public String determineType() { 
		String meldType = "";
		boolean groupFlag = true;
		boolean runFlag = true;
		int nonSequentialNumbers = 0;
		int jokerCount = 0;
		ArrayList<String> observedColors = new ArrayList<String>();
		ArrayList<Integer> observedNumbers = new ArrayList<Integer>();
		
		for(Tile t: tiles){
			//if  the color repeats it can't be a group
			if(observedColors.contains(t.getColor()))groupFlag = false;
			
			//if  numbers don't repeat it can't be a group
			if(observedNumbers.size() > 1) {
				if (!observedNumbers.contains((t.getNumber()))) groupFlag = false;
			}
			
			//if colors don't repeat it must not be a run
			if(observedColors.size() > 1) {
				if (!observedColors.contains((t.getColor()))) runFlag = false;
			}
			if(t.getColor().equals("J"))jokerCount++;
			observedColors.add(t.getColor());
			observedNumbers.add(t.getNumber());
		}
		
		
		Collections.sort(observedNumbers);
		
		//if the sequence if broken more than once (always broken once by the end of the sequence)
		for(Integer i: observedNumbers) {
			if(!observedNumbers.contains(i+1)) {
				nonSequentialNumbers++;
			}
		}

		if(nonSequentialNumbers > 1)runFlag=false;
		
		if (observedNumbers.size() <3) {
			runFlag=false;
			groupFlag=false;
		}
		
		if(!groupFlag && !runFlag ) meldType ="NO MELD TYPE";
		if(groupFlag)return "GROUP";
		if(runFlag)return "RUN";
		return "INVALID MELD";
	}
	
	public String printMeld() {
		String returnString = new String();
		returnString += this.determineType();
		for(Tile t: tiles) {
			returnString += ("\t"+t.toString());
		}
		return returnString;
	}
	public String toString() {
		
		String returnString = "";
		returnString  += determineType()+" ";
		returnString += "{";
		for(Tile t: tiles) {
			if(returnString.charAt(returnString.length()-1) == '{'){
				returnString += t.toString()+""; 
			}else {
				returnString +=","+t.toString();  
			}
		}
		returnString += "}";
		return returnString;
	}
	
	public boolean validate() {
		if(this.determineType() != "INVALID MELD")return true;
		return false;
	}

	public boolean isEmpty() {
		return tiles.isEmpty();
	}
}



