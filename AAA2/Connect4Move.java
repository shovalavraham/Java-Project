
package AAA;

public class Connect4Move {
	
	public int value; // Game value of this move
	public int move; // Number of pit to be emptied
	
	public Connect4Move(int value, int move)
	{
		this.value = value;
		this.move = move;
	}
}
/**
 * An interface for the game state of the Connect 4 game.
 *
 */

