package AAA;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Connect4ViewText implements Connect4View {
	
	private Scanner input; // Hold the input stream
	public Connect4ViewText (){
	input = new Scanner(System.in);
	}
	/**
	 * Displays the current board
	 * @param state current state of the game
	 */
	public void display (Connect4State state) {
		char[][] board = state.getBoard();
		System.out.println();
		
		for (int r = Connect4Game.ROWS-1; r>=0; r--) {
			System.out.println(board[r][0] + " " + board[r][1] + " " + board[r][2] + " " + board[r][3] + " " + board[r][4] + " " + board[r][5] + " " + board[r][6]);
		}
	}
	/**
	 * Asks the user for a move
	 * The move will be in the range 0 to Connect4State.COLS-1.
	 * @param state current state of the game
	 * @return the number of the move that player chose
	 */
	public int getUserMove(Connect4State state) {
		int col; // The pit under consideration
		char [][] board = state.getBoard();
		System.out.println();
		
		col = getIntAnswer("Column to drop a checker into, " + state.getPlayerToMove().getName() + "? ");
		//System.out.println(board[1][col]);
		
		while ((col < 1) || (col > Connect4Game.COLS) || (board[5][col-1] != '.'))
		{
			System.out.println("Illegal move. Try again.");
			col = getIntAnswer("Column to drop a checker into? ");
		}
		
		return col; // Adjust to player's side
	}
	/**
	 * Reports the move that a player has made.
	 * The move should be in the range 0 to Connect4State.COLS-1.
	 * @param chosenMove the move to be reported
	 * @param name the player's name
	 */
	public void reportMove (int chosenMove, String name) {
		System.out.println("\n" + name + " drops a Checker in Column " + chosenMove);
	}
	
	/**
	 * Ask the user the question and return the answer as an int
	 * @param question the question to ask
	 * @return The depth the player chose
	 */
	 public int getIntAnswer (String question) {
		 int answer = 0;
		 boolean valid = false;
		
		 // Ask for a number
		 System.out.print(question + " ");
		 while(!valid) {
			 try {
				 answer = input.nextInt();;
				 valid = true; // If got to here we have a valid integer
			 }
			 catch(InputMismatchException ex) {
				 reportToUser("That was not a valid integer");
				 valid = false;
				 input.nextLine(); // Throw away the rest of the line
				 System.out.print(question + " ");
			 }
		 }
		 input.nextLine(); // Throw away the rest of the line
		
		 return answer;
	 }
	
	 /**
	 * Convey a message to user
	 * @param message the message to be reported
	 */
	 public void reportToUser(String message) {
		 // Reports something to the user
		 System.out.println(message);
	 }
	
	 /**
	 * Ask the question and return the answer
	 * @param question the question to ask
	 * @return the answer to the question
	 */
	 public String getAnswer(String question) {
		 System.out.print(question);
		 
		 return input.nextLine();
	 }
 
}