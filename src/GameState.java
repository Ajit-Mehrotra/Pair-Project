
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
		} else if(state == 1) {
			cellArray[row][col].setWhite();
		}else if(state == 2) {
			cellArray[row][col].setBlack();
		}


	}
	private int playerTurn(boolean turn) {
		if(turn) {
			return 1; //return white int 
		}else {
			return 2;
		}
	}
	// Should create an array for this that has a true and false for each player. This should be only a couple lines long
	public boolean isPlaceable(Cell potentialCell) {
		//potentialCell is the cell that the user is trying to use
		int row = potentialCell.getRow();
		int col = potentialCell.getCol();

		/*if anything goes wrong, make sure to check over here for any intialization issues. 
		It was saying for all of these variables that they might not have been intialized*/
	

		int playerTurn = playerTurn(turn);

		//creates a new array and sets contents in that array equal to cellArray's states
		for(int rw = 0; rw < 8; rw ++) {
			for(int cl = 0; cl< 8; cl++) {
				currentBoard[rw][cl] = cellArray[rw][cl].getState(); 
			}
		}


		// REMEBER TO ADD THIS LINE OF CODE, IF THE STATUS OF THE POTENTIAL PIECE IS NOT EXMPTY, RETURN FALSE
		if(potentialCell.getState() == 0 ) {

      
      // If it contains same piece vertically
            for (int a = 0; a < 8; a++) {
                if(row-1 < 0){
                    if (currentBoard[a][col] == playerTurn &&  currentBoard[row+1][col] == playerTurn(!turn) ) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }else if(row+1 > 7){
                    if (currentBoard[a][col] == playerTurn &&  currentBoard[row-1][col] == playerTurn(!turn) ) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }else{
                    if (currentBoard[a][col] == playerTurn && (currentBoard[row-1][col] == playerTurn(!turn) || currentBoard[row+1][col] == playerTurn(!turn) )) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }

            }

            for (int a = 0; a < 8; a++) {
                if(col-1 < 0){
                    if (currentBoard[row][a] == playerTurn &&  currentBoard[row][col+1] == playerTurn(!turn) ) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }else if(col+1 > 7){
                    if (currentBoard[row][a] == playerTurn &&  currentBoard[row][col-1] == playerTurn(!turn) ) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }else{
                    if (currentBoard[row][a] == playerTurn && (currentBoard[row][col+1] == playerTurn(!turn) || currentBoard[row][col-1] == playerTurn(!turn) )) {
                        System.out.println("Row: " + row + " " + "Col: " + col );
                        return true;
                    }
                }

            }
      
			for(int a = 0; a < 8; a++) {
				if(a < 7 && (row+a > 8 || col+a > 7)) {
					if(currentBoard[row - (7-a)][col - (7-a)] == playerTurn(!turn)) {
						return true;
					}
				}else if(row-a < 0 || col-a < 0) { //theoretically shouldn't go to this line as it 
					//is always increasing until it is > 7 then it hits first if statement
					if(currentBoard[row + (7-a)][col + (7-a)] == playerTurn(!turn)) {
						return true;
					}
				}else {
					if( (currentBoard[row + a][col + a] == playerTurn(!turn)) ) {
						return true;
					}
				}

			}
			return false;
		}else {
			return false;
		}
	
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

