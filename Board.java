/*
Name: Manan Vij
File: Board.java
Purpose: Holds all possible aspects needed for a board for both player and AI. Battle.java uses 3 boards


*/


public class Board{
	//Variables
	final int ROW;
	final int COL;
	String board[][];
	String colNames[] = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; // what to write for columns
	String rowNames [] = {"0","1","2","3","4","5","6","7","8","9"}; // what to write for rows

	public Board(){ //Constructor
		ROW = 11; // one higher than lenght because of indexes on side of board
		COL = 11; // one higher than lenght because of indexes on side of board
		board = new String[ROW][COL]; // create a board
		for (int i = 0 ; i < ROW; i++){ // when creating a board, need to initialize it with dashes
				for (int j = 0; j < COL; j++) {
					board[i][j] = "-";
				}
			}
	}

	public void showBoard(){ // shows board
		int colCounter = 0;
		for (int i = 0 ; i < ROW; i++){ //iterate through rows
				for (int j = 0; j < COL; j++) { //iterate through columns
					if (i == 0){
						System.out.print(""+colNames[j] + "  "); // print indexes of board
					}
					else {
						if (j == 0){
							System.out.print(""+rowNames[colCounter] + "  "); // print indexes of board
							colCounter++;
						}
						else{
							System.out.print(""+ board[i][j]+ "  "); // print value at each location
						}
					}

				}
				System.out.println(""); // to make a new line for next row
		}
	}

	public void shipLocation(int[] shipCoordinate){ // updates ship location on board
		// get necessary coordinates for ship
		int startX = shipCoordinate[0];
		int startY = shipCoordinate[1];
		int endX = shipCoordinate[2];
		int endY = shipCoordinate[3];


		// will check if can or can not place ship
		boolean checkerX = false;
		boolean checkerY = false;

		int temp; // used for switching variables

		if (startX == endX){
			if (endY < startY){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

				// if end is smaller than start, switch value of variables
				temp = endY;
				endY = startY;
				startY = temp;
			}

			for (int i = startY; i <= endY; i++){ // this does a 'ghost' pass to check if the ship can be placed; if yes, checker becomes true
				if (board[startX][i].equals("+")){
					checkerX = true;
				}
			}
			if (!checkerX){ // if ship can be placed, update values on board
				for (int i = startY; i <= endY; i++){
					board[startX][i] = "+"; // change value to show ship
				}
			}
		}

		if (startY == endY){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

			// if end is smaller than start, switch value of variables
			if (endX < startX){
				temp = endX;
				endX = startX;
				startX = temp;
			}

			for (int i = startX; i <= endX; i++){ // this does a 'ghost' pass to check if the ship can be placed; if yes, checker becomes true
				if (board[i][startY].equals("+")){
					checkerY = true;
				}
			}
			if (!checkerY) { // if ship can be placed, update values on board
				for (int i = startX; i <= endX; i++){
					board[i][startY] = "+"; // change value to show ship
					}
				}
			}

	}

	public boolean registerAttack(int attackX, int attackY){ // register attack on a board for ships
		// create markers for each type of attack
		String hit = "H";
		String miss = "M";


		if (board[attackX][attackY].equals("+")){ // hit is present if the attack is at the location of the ship, marked by a '+'
			board[attackX][attackY] = hit;
			System.out.println("Hit");
			return true; // return true that hit occureed
		}

		else{
			board[attackX][attackY] = miss; // hit does not occur, mark location
			System.out.println("Miss");
		}
		return false; // hit did not occur
	}

	public void markAttack(int attackX, int attackY, boolean sucess){ // show attack on a board for attack
		if (sucess) {
			board[attackX][attackY] = "@"; // change the value if the computer ship was hit
		}
		board[attackX][attackY] = "X"; // mark that ship was not hit with an X so no double hit

	}

	public boolean doesOverlap(int startX, int startY, int endX, int endY){ //Determines whether ships overlap
		boolean checker = false;
		int temp;
		if (startX == endX){
			if (endY < startY){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

				// if end is smaller than start, switch value of variables
				temp = endY;
				endY = startY;
				startY = temp;
			}

			for (int i = startY; i <= endY; i++){ // this does a 'ghost' pass to check if the ship can be placed; if yes, checker becomes true
				if (board[startX][i].equals("+")){
					checker = true;
				}
			}
		}

		if (startY == endY){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

			// if end is smaller than start, switch value of variables
			if (endX < startX){
				temp = endX;
				endX = startX;
				startX = temp;
			}

			for (int i = startX; i <= endX; i++){ // this does a 'ghost' pass to check if the ship can be placed; if yes, checker becomes true
				if (board[i][startY].equals("+")){
					checker = true;
				}
			}
		}
		return checker;



	}



}
