package core;


public class Tile implements Comparable<Tile> {
	
	private int	number;
	private String 	color;
	private boolean visible;
	private int value;
	
	//Constructors 
	public Tile(int number, String color ) {
		visible 	=	true;
		this.number =	number;
		this.color 	=	color;
		setValue();
	}
	
	public Tile(int number, String color, boolean visible) {
		this.visible 	=	visible;
		this.number 	= 	number;
		this.color 		= 	color;
		setValue();
	}

	public Tile() {
		
	}
	
	//Getters
	public int 	getNumber() 	{return number;}
	public String 	getColor()		{return String.valueOf(color.charAt(0)) ;}
	public boolean 	getVisibile()	{return visible;}
	public int getValue() {return value;}
	
	//Setters
	public void setVisiblity(boolean visible) {this.visible = visible;}
	

	//Returns String that represents Tile in a String format
	public final String printTile(){
		if(!visible) {
			return "A hidden tile";
		}
		else {
			return String.valueOf(getColor().charAt(0))+ String.valueOf(getNumber());
		}
		
	}
	
	//Returns positive integer if this value is greater. 0 if even. Negative integer if this value is lesser.
	public int compareTo(Tile tile2) {
		return getValue() - tile2.getValue();
	}
	
	//Method to automatically set value variable automatically based off of color and number. Only meant to be used within class.
	//Range between int 1 - 48
	private void setValue() {
		int count = 0;
		if (getColor() == "R"){
			count += 12 * 0;
		}else if(getColor() == "B") {
			count += 12 * 1;
		}else if (getColor() == "G") {
			count += 12 * 2;
		}else if (getColor() == "O") {
			count += 12 * 3;
		}
		count += getNumber();
		value = count;
	}

	
}



