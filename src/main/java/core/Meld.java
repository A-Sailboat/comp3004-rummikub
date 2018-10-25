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
	
		
	public int add(Tile aTile) {tiles.add(aTile);}
	public void remove(Tile aTile) {tiles.remove(aTile);}
	
	
	public String determineType() {}
	public String toString() {}
	public boolean validate() {}
	
}



