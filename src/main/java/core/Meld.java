package core;


import java.util.ArrayList;
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
	
	public int  size() {return tiles.size();}
	public void add(Tile aTile) {
		tiles.add(aTile);
		this.type = this.determineType();
	}
	public void remove(Tile aTile) {
		tiles.remove(aTile);
		this.type = this.determineType();
	}
	
	
	public String determineType() { 
		
		boolean groupFlag = true;
		boolean runFlag = true;

		for(int i = 0; i<tiles.size()-1; i++){
			if (tiles.get(i).getColor() == tiles.get(i+1).getColor()){groupFlag = false;}
			else{runFlag = false;}
			
			if (tiles.get(i).getNumber() == tiles.get(i+1).getNumber()-1){groupFlag = false;}
			else{runFlag = false;}
			
			if (tiles.get(i).getNumber() != tiles.get(i+1).getNumber()){groupFlag = false;}
		}
		
		if(groupFlag && runFlag)return "ERROR";
		if(groupFlag)return "GROUP";
		if(runFlag)return "RUN";
		return "INVALID MELD";
	}
	
	public String toString() {return null;}
	public boolean validate() {
		if(this.determineType() != "INVALID MELD")return true;
		return false;
	}
}



