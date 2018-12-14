package core;


public class Tile implements Comparable<Tile> {
	
	private int	number;
	private String 	color;
	private int value;
	
	//Constructors 
	public Tile(int number, String color ) {
	
		this.number =	number;
		this.color 	=	color;
		setValue();
	}
	
	public Tile(String twoPartId) {
		
		if(twoPartId.charAt(0) == 'B') {
			this.color = "B";
		}else if(twoPartId.charAt(0) == 'G') {
			this.color = "G";
		}else if(twoPartId.charAt(0) == 'R') {
			this.color = "R";
		}else if(twoPartId.charAt(0) == 'O') {
			this.color = "O";
		}else if(twoPartId.charAt(0) == 'J') {
			this.color = "J";
		}
		this.number = Integer.parseInt(twoPartId.substring(1));
		
		setValue();
		//System.out.println( this.color+this.number+this.value);
	}
	
	public Tile(int number, String color, boolean visible) {
		this.number 	= 	number;
		this.color 		= 	color;
		setValue();
	}
	
	public boolean equals(Tile t) {
		if(this.number == t.number) {
			if(this.color == t.color) {
				if(this.value == t.value) {
					return true;
				}
			}
		}
		return false;
	}

	public Tile() {
		
	}
	
	//Getters
	public int 	getNumber() 	{return number;}
	public String 	getColor()		{return String.valueOf(color.charAt(0)) ;}
	public int getValue() {return value;}
	
	//Setters
	

	//Returns String that represents Tile in a String format
	public String printTile(){
		return String.valueOf(getColor().charAt(0))+ String.valueOf(getNumber());
		
		
	}
	
	//Returns positive integer if this value is greater. 0 if even. Negative integer if this value is lesser.
	public int compareTo(Tile tile2) {
		return getValue() - tile2.getValue();
	}
	public String toString() {
		return getColor()+getNumber();
	}
	
	//Method to automatically set value variable automatically based off of color and number. Only meant to be used within class.
	//Range between int 1 - 48
	private void setValue() {
		int count = 0;
		if (getColor().equals("R")) {
			count += 12 * 0;
		}else if(getColor().equals("B")) {
			count += 12 * 1;
		}else if (getColor().equals("G")) {
			count += 12 * 2;
		}else if (getColor().equals("O")) {
			count += 12 * 3;
		}else if (getColor().equals("J")) {
			count = 2000;
		}
		
		count += getNumber();
		value = count;
	}

	
}



