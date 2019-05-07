package AAA;

public class Connect4Game implements Connect4State {
	
	private char [][] board;
	private int playerToMoveNum; // 0 or 1 for first and second player
	private Player [] players; // Array of the two players
	//private Connect4View view;
	public final static int ROWS = 6; // Board height
	public final static int COLS = 7; // Board width
	public final static char EMPTY = '.'; // Indicate empty place
	public final static char CHECKER0 = 'X'; // Indicate the first player's checker
	public final static char CHECKER1 = 'O'; // Indicate second player's checker
	public final static char [] CHECKERS = {CHECKER0, CHECKER1};
	 /**
	 * Constructs a game in the initial state
	 * @param playerNum the player whose move it is
	 * @param thePlayers the player objects
	 * @param aView the view in the model-view-controller model
	 */
	 public Connect4Game(int playerNum, Player [] thePlayers) {
		 char [][] initBoard = new char[ROWS][COLS];
		 
		 for (int j = 0; j < ROWS; j++) {
			 for (int k = 0; k < COLS; k++) {
				 initBoard[j][k] = '.';
			 }
		 }
		 
		 board = initBoard;
		 playerToMoveNum = playerNum;
		 players = thePlayers;
		 //view = aView; // needed?
	 }
	
	 /**
	 * Constructs a game given a board
	 * @param playerNum the player whose move it is
	 * @param thePlayers the player objects
	 * @param initBoard the board to copy into this state
	 */
	 public Connect4Game(int playerNum, Player [] thePlayers, char [][] initBoard) {
		 board = new char[ROWS][COLS];
		
		 for (int i = 0; i < ROWS; i++) {
			 for (int j =0; j<COLS; j++) {
				 board[i][j] = initBoard[i][j];
			 }
		 }
		
		 playerToMoveNum = playerNum;
		 players = thePlayers;
	 }
	/**
	 * Gets a 2-D array representing the board.
	 * The first subscript is the row number and the second the column number.
	 * The bottom of the board is row 0 and the top is row ROWS-1.
	 * The left side of the board is column 0 and the right side is column COLS-1.
	 * @return the board
	 */
	 public char [][] getBoard() {
		 return board;
	 }
	
	 /**
	 * Gets an array holding 2 Player objects
	 * @return the players
	 */
	 public Player [] getPlayers() {
		 return players;
	 }
	
	 /**
	 * Gets the number of the player whose move it is
	 * @return the number of the player whose move it is
	 */
	 public int getPlayerNum () {
		 return playerToMoveNum;
	 }
	
	 /**
	 * Gets the Player whose turn it is to move
	 * @return the Player whose turn it is to move
	 */
	 public Player getPlayerToMove() {
		 return players[playerToMoveNum];
	 }
	 /**
	 * Is this move valid?
	 * @param col column where we want to move
	 * @return true if the move is valid
	 */
	 public boolean isValidMove(int col) {
		 if (board[ROWS-1][col-1] == EMPTY){
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	
	 /**
	 * Make a move, dropping a checker in the given column
	 * @param col the column to get the new checker
	 */
	 public void makeMove(int col) {
		 int r = 0;
		 
		 while (board[r][col-1] != EMPTY && r < ROWS) {
			 r++;
		 }
		 
		 //if (r >= ROWS)
		 board[r][col-1] = CHECKERS[playerToMoveNum];
		 playerToMoveNum = 1 - playerToMoveNum;
	 }
	
	 /**
	 * Is the board full?
	 * @return true if the board is full
	 */
	 public boolean isFull() {
		 boolean full = true;
		 
		 for (int c = 0; c < COLS; c++) {
			 if (board[ROWS-1][c] == EMPTY) {
				 full = false;
			 }
		 }
		 
		 return full;
	 }
	 /**
	 * Decides if the game is over
	 * @return true if the game is over
	 */
	 public boolean gameIsOver() {
		 boolean gameOver = false;
		 
		 if (isFull()) {
			 gameOver = true;
		 }
		 else if (connectFourAnywhere()) {
			 gameOver = true;
		 }
		 
		 return gameOver;
	 }
	
	
	 /**
	 * Get the score of a board
	 */
	 public int score(){
		 int score = 0;
		 
		 for (int r= 0; r < ROWS; r++) {
			 if (r <= ROWS-4) {
				 for (int c = 0; c < COLS; c++) {
					 score += score(r, c);
				 }
			 }
			 else {
				 for (int c = 0; c <= COLS-4; c++) {
					 score += score(r, c);
				 }
			 }
		 }
		
		 return score;
	 }
	
	 /**
	 * Helper method to get the score of a board
	 */
	 public int score(int row, int col){
		 int score = 0;
		 boolean unblocked = true;
		 int tally = 0;
		 //int r, c;
		 
		 if (row < ROWS-3) {
			 //check up
			 unblocked = true;
			 tally = 0;
			 
			 for (int r=row; r<row+4; r++) {
			
				 if (board[r][col] == CHECKERS[1-playerToMoveNum]) {
					 unblocked = false;
				 }
				 if (board[r][col] == CHECKERS[playerToMoveNum]) {
					 tally ++;
				 }
			 }
			 
			 if (unblocked == true) {
				 score = score + (tally*tally*tally*tally);
			 }
			
			 if (col < COLS-3) {
				 //check up and to the right
				 unblocked = true;
				 tally = 0;
				 
				 for (int r=row, c=col; r<row+4; r++, c++) {
					 if (board[r][c] == CHECKERS[1-playerToMoveNum]) {
						 unblocked = false;
					 }
					 if (board[r][c] == CHECKERS[playerToMoveNum]) {
						 tally ++;
					 }
				 }
				 
				 if (unblocked == true) {
					 score = score + (tally*tally*tally*tally);
				 }
			 }
		 }
		 
		 if (col < COLS-3) {
			 //check right
			 unblocked = true;
			 tally = 0;
			 
			 for (int c=col; c<col+4; c++) {
				 if (board[row][c] == CHECKERS[1-playerToMoveNum]) {
					 unblocked = false;
				 }
				 if (board[row][c] == CHECKERS[playerToMoveNum]) {
					 tally ++;
				 }
			 }
			 
			 if (unblocked == true) {
				 score = score + (tally*tally*tally*tally);
			 }
			
			 if (row > 2) {
				 //check down and to the right
				 unblocked = true;
				 tally = 0;
				 
				 for (int r=row, c=col; c<col+4; r--, c++) {
					 if (board[r][c] == CHECKERS[1-playerToMoveNum]) {
						 unblocked = false;
					 }
					 
					 if (board[r][c] == CHECKERS[playerToMoveNum]) {
						 tally ++;
					 }
				 }
				 
				 if (unblocked == true) {
					 score = score + (tally*tally*tally*tally);
				 }
			 }
		 }
		
		 return score;
	
	 }
	
	
	 /**
	 * Check the board to see if there is a Connect Four anywhere
	 * @return true if there is a connect 4 somewhere
	 */
	 public boolean connectFourAnywhere() {
		 boolean connect4 = false;
		 
		 for (int r= 0; r < ROWS && connect4 == false; r++) {
			 if (r <= ROWS-4) {
				 for (int c = 0; c < COLS && connect4 == false; c++) {
					 connect4 = connectFour(r, c);
				 }
			 }
			 else {
				 for (int c = 0; c <= COLS-4 && connect4 == false; c++) {
					 connect4 = connectFour(r, c);
				 }
			 }
		 }
		
		 return connect4;
	 }
	
	 /**
	 * Given a row and column, check for a connect 4 from that position
	 * @return true if there is a connect 4.
	 *
	 */
	 public boolean connectFour(int row, int col) {
		 boolean c4 = false;
		 //int r, c;
		 
		 if (row < ROWS-3) {
			 //check up
			 c4 = true;
			 
			 for (int r=row; r<row+4; r++) {
				 if (board[r][col] != CHECKERS[1-playerToMoveNum]) {
					 c4 = false;
				 }
			 }
			
			 if (col < COLS-3 && c4==false) {
				 //check up and to the right
				 c4 = true;
				 
				 for (int r=row, c=col; r<row+4; r++, c++) {
					 if (board[r][c] != CHECKERS[1-playerToMoveNum]) {
						 c4 = false;
					 }
				 }
			 }
		 }
		 
		 if (col < COLS-3 && c4==false) {
			 //check right
			 c4 = true;
			 
			 for (int c=col; c<col+4; c++) {
				 if (board[row][c] != CHECKERS[1-playerToMoveNum]) {
					 c4 = false;
				 }
			 }
			
			 if (row > 2 && c4==false) {
				 //check down and to the right
				 c4 = true;
				 
				 for (int r=row, c=col; c<col+4; r--, c++) {
					 if (board[r][c] != CHECKERS[1-playerToMoveNum]) {
						 c4 = false;
					 }
				 }
			 }
		 }
		
		 return c4;
	 }

}