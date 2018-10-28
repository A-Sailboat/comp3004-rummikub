package core;

import java.util.Comparator;

public class Tile implements Comparable<Tile> {
	
	private Integer	number;
	private String 	color;
	private boolean visible;
	
	public Tile(Integer number, String color ) {
		visible 	=	false;
		this.number =	number;
		this.color 	=	color;
	}
	public Tile(Integer number, String color, boolean visible) {
		this.visible 	=	visible;
		this.number 	= 	number;
		this.color 		= 	color;
	}

	public Tile() {
		
	}
	public Integer 	getNumber() 	{return number;}
	public String 	getColor()		{return color;}
	public boolean 	getVisibile()	{return visible;}
	
	public void setVisiblity(boolean visible) {this.visible = visible;}
	

	@Override
	public final String toString(){
		if(!visible) {return "A hidden tile";}
		else {return getColor().charAt(0)+ getNumber().toString();}
		
	}
	public int compareTo(Tile tile2) {
		int a = this.getNumber() - tile2.getNumber(); 
  	 	if (a == 0) {return this.getColor().compareTo(tile2.getColor());}
  	 	return a;
	}

	
}



