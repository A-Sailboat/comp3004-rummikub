package core;

public interface Ai {
	
	String ai();

}

class noAi implements Ai{
	public String ai() {
		return "No ai";
	}
}

class oneAi implements Ai{
	public String ai() {
		return "AI strategy 1";
	}
}

class twoAi implements Ai{
	public String ai() {
		return "AI strategy 2";
	}
}

class threeAi implements Ai{
	public String ai() {
		return "AI strategy 3";
	}
}

