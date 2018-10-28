package core;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Meld> melds = new ArrayList<Meld>();
	boolean complete = false;
	
	Meld get(int i){return melds.get(i);}
	Meld remove(int i){return melds.remove(i);}
	
	@Overide 
	String toString(){}
	
}

