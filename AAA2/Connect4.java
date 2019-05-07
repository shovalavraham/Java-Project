package AAA;

import java.awt.Color;

import javax.swing.*; 

public class Connect4 {
	
	public static void main(String args[])
	 {
		JFrame theFrame = new JFrame();
		theFrame.setTitle("4Connect");
		theFrame.setSize(400, 400);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder("4Connect"));
		mainPanel.setBackground(Color.PINK);
		theFrame.setContentPane(mainPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);
		
		 Connect4View view = new Connect4ViewText();
		// Connect4View view = new Connect4ViewGraphical();
		
		
		 Player [] players = new Player[2]; // Array to hold the players
		
		 // Initialize the players
		 players[0] = makePlayer(view, "first");
		 players[1] = makePlayer(view, "second");
		
		 // Hold current game state
		 Connect4Game state = new Connect4Game(0, players);
		 view.display(state);
		 
		 while (!state.gameIsOver())
		 {
			 int move = state.getPlayerToMove().getMove(state, view);
			 state.makeMove(move);
			 view.display(state);
		 }
		 
		 if (state.isFull())
			 view.reportToUser("It is a draw");
		 else
			 view.reportToUser(players[1 - state.getPlayerNum()].getName() + " wins!");
		 
	 }
		 
	 public static Player makePlayer(Connect4View view, String playerMsg) {
		 String playerName = view.getAnswer("Enter the name of the " + playerMsg +
		 " player." + "\n(Include 'Computer' in the name of a computer player) ");
		 
		 if(playerName.contains("Computer")) {
			 int depth = -1;
			 
			 while(depth<0 || depth>5)
				 depth = view.getIntAnswer("How far should I look ahead? Enter a number between 0-5");
			 
			 return new ComputerConnect4Player(playerName, depth);
		 }
		 else
			 return new HumanConnect4Player(playerName);
		 
	 }
	 
}