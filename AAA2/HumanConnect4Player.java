package AAA;

public class HumanConnect4Player extends Player {
	/**
	 * @param name name of the human player
	 */
	public HumanConnect4Player (String name) {
		super(name);
	}
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		// Get a move for the user
		return view.getUserMove(state);
	}
}