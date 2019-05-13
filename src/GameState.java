import java.util.ArrayList;

public class GameState {
	//double check if static is correct. I think it is. 
	private static Cell[][] cellArray = new Cell[8][8]; // current state of cells on board
	private boolean turn; //true -> player1Turn false->player2Turn
	private static boolean[][] placeableArray = new boolean[8][8];
	int [][] currentBoard = new int[8][8];	



	//Constructor
	GameState() {
		for(int a = 0;a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				cellArray[a][b] = new Cell();
				//sets the row and col 
				cellArray[a][b].setRow(a);
				cellArray[a][b].setCol(b);
			}
		}

		//Initialize the states of the beginning pieces (pieces in the middle)
		for(int i = 3; i < 5; i++) {
			for(int a = 3; a<5; a++) {
				if(i == a) {
					cellArray[i][a].setWhite();
				}else {
					cellArray[i][a].setBlack();
				}
			}
		}

	}

	public static boolean[][] getPlaceableArray() {
		return placeableArray;
	}

	public void setTurn(boolean turnSet) {
		turn = turnSet;
	}

	public boolean getTurn() {
		return turn;
	}

	public static Cell[][] getCellArray() {
		return cellArray;
		// return the reference for the array of cells
	}
	public void updateCellArray(int row, int col, int state){
		if(state == 0) {
			cellArray[row][col].setEmpty();
			currentBoard[row][col] = 0;
		} else if(state == 1) {
			cellArray[row][col].setWhite();
			currentBoard[row][col] = 1;
		}else if(state == 2) {
			cellArray[row][col].setBlack();
			currentBoard[row][col] = 2;
		}


	}
	public int playerTurn(boolean turn) {
		if(turn) {
			return 1; //return white int 
		}else {
			return 2;
		}
	}
	// Should create an array for this that has a true and false for each player. This should be only a couple lines long
	public boolean isPlaceable(Cell potentialCell) {

		int x = potentialCell.getCol();
		int y = potentialCell.getRow();
		boolean placeable = false;
		ArrayList<Cell> check = new ArrayList<Cell>();

		if(potentialCell.getState() != 0) {
			return false;
		}

		//up
		for(int i=0;y-i>-1;i++) {
			Cell checkCell = cellArray[x][y-i];
			check.add(checkCell);
		}

		//down
		for(int i=0;i+x<8&&i+y<8;i++) {

		}
		//right
		for(int i=0;i+x<8&&i+y<8;i++) {

		}
		//left
		for(int i=0;i+x<8&&i+y<8;i++) {

		}
		//up right
		for(int i=0;i+x<8&&i+y<8;i++) {

		}
		//up left
		for(int i=0;i+x<8 &&i+y<8;i++) {

		}
		//down left
		for(int i=0;i+x<8&&i+y<8;i++) {

		}
		//down right
		for(int i=0;i+x<8&&i+y<8;i++) {

		}

	}

	//look at a column, row, or diagonal. If there is a piece that is the player's color is blocked by an opponent's color and there is no empty piece in between, placeable is true

	private boolean properArray(ArrayList<Cell> potentialAffected) {
		boolean returnVal = true;
		int startColor = potentialAffected.get(0).getState();
		int otherColor;
		if(startColor == 2) {
			otherColor = 1;
		}else {
			otherColor = 2;
		}
		for(int i=1; i < potentialAffected.size()-1; i++) {
			if(otherColor != potentialAffected.get(i).getState()) {
				returnVal = false;
			}
		}
		return returnVal;
	}



	public int PlayerTwoPoints() {
		int points = 0;
		for(int a = 0; a<8; a++) {
			for(int b = 0; b < 8; b++) {
				if(currentBoard[a][b] == 2) {
					points++;
				}
			}
		}
		return points;
	}
	public int PlayerOnePoints() {
		int points = 0;
		for(int a = 0; a<8; a++) {
			for(int b = 0; b < 8; b++) {
				if(currentBoard[a][b] == 1) {
					points++;
				}
			}
		}
		return points;
	}

	//look at a column, row, or diagonal. If there is a piece that is the player's color is blocked by an opponent's color and there is no empty piece in between, placeable is true

	public int Winner(Player player1, Player player2) {
		if(player1.returnPoints(1) > player2.returnPoints(2)) {
			return 1; // Player 1 (White Wins)
		}else if(player1.returnPoints(1) < player2.returnPoints(2)) {
			return 2; // Player 2 (Black Wins)
		}else {
			return 0; // 0 in this case means a draw 
		}
	}           
}

