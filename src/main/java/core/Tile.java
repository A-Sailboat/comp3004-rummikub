package core;

import java.util.Comparator;

public class Tile implements Comparable<Tile> {
	
	private Integer	number;
	private String 	color;
	private boolean visible;
	private int value;
	
	public Tile(Integer number, String color ) {
		visible 	=	false;
		this.number =	number;
		this.color 	=	color;
		setValue();
	}
	public Tile(Integer number, String color, boolean visible) {
		this.visible 	=	visible;
		this.number 	= 	number;
		this.color 		= 	color;
		setValue();
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
	
	//Method to automatically set value variable automatically based off of color and number. Only meant to be used within class.
	//Range between int 1 - 48
	private void setValue() {
		int count = 0;
		if (color == "R"){
			count += 12 * 0;
		}else if(color == "B") {
			count += 12 * 1;
		}else if (color == "G") {
			count += 12 * 2;
		}else if (color == "O") {
			count += 12 * 3;
		}
		count += number;
		value = count;
	}

	
}



