package core;

public interface Ai {
	
	String ai();

}

class NoAi implements Ai{
	public String ai() {
		return "No ai";
	}
}

class OneAi implements Ai{
	public String ai() {
		return "AI strategy 1";
	}
}

class TwoAi implements Ai{
	public String ai() {
		return "AI strategy 2";
	}
}

class ThreeAi implements Ai{
	public String ai() {
		return "AI strategy 3";
	}
}

