package core;

public class RummikubView {
	private boolean initialDraws;
	private boolean allHands;
	private boolean playerHand;
	private boolean board;
	private boolean turnManagement;
	private int state = 0;
	
	
	public void draw(RummikubModel model){
		int i = model.getState();
		divider();
		if(i==0){ //Initial State, Nothing is drawn at this time.
			
			
		}if(i==1){ //Opening State, Draw the initial draws, and all hands
			drawInitialDraws(model.getTurnOrder(), model.getCurrentPlayer());
			
			
		}if(i==2){ //Turn State, Show Player Hand, the board, and highlight new melds
			currentPlayerHand(model.getCurrentPlayer());
			drawBoard(model.getTurnOrder(),model.getBoard(),model.getTurnNumber());
			
		}if(i==3){//Mid-Turn State, for when player is  actively altering hand
			
			
		}if(i==4){//Printing new tile
			drewTile(model.getCurrentPlayer(), model.getNewTile());
			
		}if(i==5){//Draw all hands
			drawAllHands(model.getTurnOrder());
			
		}if(i==6){
	
			
		}if(i==7){//Returning from a Play
			drawPlay(model.getCurrentPlayer(), model.getReferanceCopy(), model.getBoard());
			
		}if(i==8){ //Ending the  game
			clear();
			exitMessage(model.getWinner());
			
		}
		
		
		
	}

	
	public void drawInitialDraws(Player[] players, Player p){
		
		System.out.println("Tiles Drawn: \n" );
		
		for(int i = 0; i< players.length; i++) {
			System.out.println(players[i].toString()+"'s Draw was "+players[i].getHand().toString());
		}
		
		System.out.println(p.toString()+"'s Draw was best so they will go first\t");
	}
	public void drawAllHands(Player[] players){
		System.out.println("Hands: \n" );
		
		for(int i = 0; i< players.length; i++) {
			/////////////////////////////////////////////////////////////////////////
			players[i].getHand().arrangeHand();/////////////////////////////////////////////////////////////////////////////////////////
			
			System.out.println(players[i]+"'s Hand\t" + players[i].getHand().toString());
			
		}
	}
	
	public void drewTile(Player p, Tile t){
		System.out.println(p.toString() +" draws one tile "+ t.toString()+ "\n");
	}
	public void printWhoseTurn(Player p, int turnNumber) {
		
		System.out.println("Turn Number:"+turnNumber);
		System.out.println(p.toString()+ "'s Turn");
	}
	
	public void drawPlay(Player p, Board oldBoard,Board newBoard ){
		System.out.print(p.toString()+" played tiles\n"	+ p.toString()+"'s new hand:"+p.getHand().toString()+
				"\n"+"The Updated Board:\n"+newBoard.toString(oldBoard)+"\n");

	}
	
	
public void drawBoard(Player[] p, Board b, int turnNumber ) {
		
		System.out.print("Melds on Board:\n");
		System.out.print(b.toString()+"\n");
		
	}
public void currentPlayerHand(Player p) {
	
	System.out.print("Your Hand:\n");
	System.out.print(p.getHand().toString()+"\n");
}

public void exitMessage(Player p) {
	divider();
	System.out.println(p.getPlayerName()+" won the game!");
	System.out.print("See you later!");
	
}
public void divider() {
	System.out.println("___________________________________________________________________________________________________");
}

public void clear() {
	for(int i = 0; i<24; i++) {
		System.out.print("\n");
	}
}
}
