package nl.sogyo.mancala;

import java.util.Scanner;

public final class Mancala
{
	private Cube firstCube;
	
    public static void main( String[] args ) {
        Mancala mancala = new Mancala();
        while (!mancala.gameHasWinner()) mancala.makeMove();
        System.out.print("\n\n==========\nGAME OVER\n==========\n");
        String[] result = mancala.getWinInfo();
        System.out.println(result[0] + " stones");
        System.out.println(result[1] + " stones");
    }
    
    public Mancala() {
    	firstCube = new Cube();
    }
    
    public void drawBoard() {
    	System.out.print("\n\n\n======\nTurn: " + getPlayersTurn() + "\n======\n\n");
    	System.out.print("Kalaha player 2: ");
    	for (int i = 13; i > 6; i--)
    		System.out.print(firstCube.getCube(i).getStock() + "\t");
    	System.out.print("\n\t\t\t");
    	for (int i = 0; i < 6; i++)	
    			System.out.print(firstCube.getCube(i).getStock() + "\t");
		System.out.println("Kalaha player 1: " + firstCube.getCube(6).getStock());
    }
    
    public boolean gameHasWinner() {
    	return firstCube.getPlayer().getState() != PlayerState.UNDECIDED;
    }
    
    public String[] getWinInfo() {
    	String[] result = {
    			"Player 1: " + firstCube.getPlayer().getStockAtEndOfGame(),
    			"Player 2: " + firstCube.getPlayer().getOpponent().getStockAtEndOfGame()
    		};
    	return result;
    }
    
    public String getPlayersTurn() {
    	return (firstCube.getPlayer().hasTurn()) ? "Player 1" : "Player 2";
    }
    
    public void makeMove() {
    	drawBoard();
    	int cube = -1;
    	do {
    		cube = UserInput.GetUserInputInteger("Pick a cube to play: ");
    		if (cube >= 1 && cube <= 6) break;
    	} while (true);
    	if (getPlayersTurn() == "Player 2") cube += 7;
    	try {
    		((Cube) firstCube.getCube(cube - 1)).playStones();
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
