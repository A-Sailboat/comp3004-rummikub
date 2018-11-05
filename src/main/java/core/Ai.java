package core;


public interface Ai {
	
	String ai();

}

class NoAi implements Ai{
	public String ai() {
		return "No ai";
	}
}

//Each Ai will decide differently how to play it's tiles. 

class OneAi implements Ai{
	public String ai() {
		return "AI strategy 1";
	}
	
	public Tile canPlayOnMeld(Meld meld) {
		return null;
	}
}

class TwoAi implements Ai{
	public String ai() {
		return "AI strategy 2";
	}
	
	public Tile canPlayOnMeld() {
		return null;
	}
}

class ThreeAi implements Ai{
	public String ai() {
		return "AI strategy 3";
	}
	
	public Tile canPlayOnMeld() {
		return null;
	}
}

