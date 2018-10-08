package core;

public class Tile  {
	
	private int 	number;
	private String 	color;
	private boolean visible;
	
	public Tile(int number, String color ) {
		visible 	=	false;
		this.number =	number;
		this.color 	=	color;
	}
	public Tile(int number, String color, boolean visible) {
		this.visible 	=	visible;
		this.number 	= 	number;
		this.color 		= 	color;
	}

	public int 		getNumber() 	{return number;}
	public String 	getColor()		{return color;}
	public boolean 	getVisibile()	{return visible;}
	
	public void setVisiblity(boolean visible) {this.visible = visible;}
	

	@Override
	public final String toString(){
		if(!visible) {return "A hidden tile";}
		else {return this.getColor() +" "+ getNumber();}
	}
	
}



