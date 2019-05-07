package AAA;

public class ComputerConnect4Player extends Player {
	
	private int depth; // Look-ahead depth
	
		 /**
		 * Constructs a computer player that uses alpha-beta pruning
		 * @author Ryan Maguire
		 * @param name
		 * @param maxDepth
		 */
	public ComputerConnect4Player(String name, int maxDepth) {
		super(name);
		depth = maxDepth;
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		
		// Returns the computer play's choice using alpha-beta search
		int move = pickMove(state, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE).move;
		view.reportMove(move, state.getPlayerToMove().getName());
		
		return move;
	}
	
	/**
	 * Uses game tree search with alpha-beta pruning to pick player's move
	 * low and high define the current range for the best move.
	 * The current player has another move choice which will get him at least low,
	 * and his opponent has another choice that will hold his losses to high.
	 *
	 * @param state current state of the game
	 * @param depth number of moves to look ahead in game tree search
	 * @param low a value that the player can achieve by some other move
	 * @param high a value that the opponent can force by a different line of play
	 * @return the move chosen
	 */
	
	private Connect4Move pickMove(Connect4State state, int depth, int low, int high) {
		
		Connect4Move currentMove; // Hold current move and its value
		Connect4Move bestMove; // Hold best move found and its value
		char[][] board = state.getBoard();
		int playerToMove = state.getPlayerNum();
		// A dummy move that will be replaced when a real move is evaluated,
		// so the column number is irrelevant.
		bestMove = new Connect4Move(Integer.MIN_VALUE, 0);
		
		// Run through possible moves
		for (int c= 1; c <= Connect4Game.COLS; c++) {
			 if (state.isValidMove(c)) { // See if legal move
				// Make a scratch copy of state
				Connect4Game copy = new Connect4Game(playerToMove, state.getPlayers(), board);
				copy.makeMove(c); // Make the move
			if (copy.gameIsOver()) {
				currentMove = new Connect4Move(Integer.MAX_VALUE, c);
			}
			else if (playerToMove == copy.getPlayerNum()) { // Did current player change?
				currentMove = pickMove(copy, depth, low, high); // No, so no depth change
				currentMove.move = c; // Remember move made
			}
			else if (depth > 0) { // Player changed, so reduce search depth
				currentMove = pickMove(copy, depth - 1, -high, -low);
				currentMove.value = -currentMove.value; // Good for opponent is bad for me
				currentMove.move = c; // Remember move made
			}
			else // Depth exhausted, so estimate who is winning by comparing kalahs
				currentMove = new Connect4Move(copy.score(), c);
			
			if (currentMove.value > bestMove.value) { // Found a new best move?
				bestMove = currentMove;
				low = Math.max(low, bestMove.value); // Update the low value, also
			}
			
			}
			//return bestMove;
		}
		
		return bestMove;
		}

}
/**
 * Run to play Connect 4
 * Can be between human players or computer players
 *
 */
