package nl.sogyo.ui;

import java.util.Scanner;
import nl.sogyo.mancala.*;
import java.util.Map;

public class UI {
	private Mancala mancala;
	
	public static void main( String[] args ) {
        UI mancalaUI = new UI();
        mancalaUI.play();
    }
	
	public UI() {
		mancala = new Mancala();
	}
	
	public void play() {
		while (!mancala.gameHasWinner()) makeMove();
        
        System.out.print("\n\n==========\nGAME OVER\n==========\n");
        Map<Player, Integer> result = mancala.getWinInfo();
        
        System.out.println(result.get(Player.PLAYER1) + " stones");
        System.out.println(result.get(Player.PLAYER2) + " stones");
	}
    
    public void drawBoard() {
    	String playerOnTurn = (mancala.getPlayersTurn() == Player.PLAYER1) ? "1" : "2";
    	
    	System.out.print("\n\n\n======\nTurn: Player " + playerOnTurn + "\n======\n\n");
    	System.out.print("Kalaha player 2: ");
    	for (int i = 13; i > 6; i--)
    		System.out.print(mancala.cubeGetStock(i) + "\t");
    	System.out.print("\n\t\t\t");
    	for (int i = 0; i < 6; i++)	
    			System.out.print(mancala.cubeGetStock(i) + "\t");
		System.out.println("Kalaha player 1: " + mancala.cubeGetStock(6));
    }
    
    public void makeMove() {
    	drawBoard();
    	int cube = -1;
    	do {
    		cube = UserInput.GetUserInputInteger("Pick a cube to play (Q to quit game): ");
    		if (cube >= 1 && cube <= 6) break;
    	} while (true);
    	if (mancala.getPlayersTurn() == Player.PLAYER2) cube += 7;
    	try {
    		mancala.cubePlayStones(cube - 1);
    	}
    	catch (InvalidMoveException ex) {
    		System.out.println("\n\nMOVE NOT VALID\n\n");
    		makeMove();
    	}
    }
}

//helper class
final class UserInput {
	private UserInput() {};
	private static Scanner reader = new Scanner(System.in);
	
	
	// Get integer from user
	public static int GetUserInputInteger(String prompt) {
		String s = GetUserInputString(prompt);
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException ex) {
			if (s.equals("Q")) System.exit(0);
			System.out.println("Sorry, but \"" + s + "\" appears not to be an integer...\nTry again please!");
			return GetUserInputInteger(prompt);
		}
	}
	
	// Get string from user
	public static String GetUserInputString(String prompt) {
		System.out.print(prompt);
		return reader.nextLine();
	}
}