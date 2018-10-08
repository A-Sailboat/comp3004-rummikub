package core;

import java.util.ArrayList;
import java.util.List;

public class Meld  {
	
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Meld() {}
	public Meld(ArrayList<Tile> tiles ) {this.tiles = tiles;}
	public List<Card> getMeld() {return tiles;}
	
		
	public int add(Tile aTile) {tiles.add(aTile);}
	public void remove(Tile aTile) {tiles.remove(aTile);}
	
	
	
	public String toString() {}
	public boolean validate() {}
	
}



