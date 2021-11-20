/*
Name: Kyler Pang
File: Ship.java
Purpose: Holds all possible aspects needed in a ship for both player and AI


*/


public class Ship{

	//[Class Variables]
	public int shipSize;
	public int[] coordinates;
	public int hits;
	public boolean diagonal;
	public boolean inBounds;

	public Ship(){ //CONSTRUCTOR
		shipSize = 2;
		coordinates = new int[4];
		hits = 0;

	}

	public Ship(int size){ //OVERLOADED CONTRUCTOR
		shipSize = size;
		coordinates = new int[4];
		hits = 0;
	}

	/*public int[] shipPos() { // Returns the shipPosition ------------> Cut out
		return shipStartEnd;
	}
	public boolean acceptPosition(int startX,  int startY, int endX, int endY) {
		if (startX == endX || startY == endY){ //Vertical/Horizontal -> True
			if (startX >= 10 || startY >= 10 || endX >= 10 ||endY >= 10){ //In bounds
				return false;
			}
			return true;
		}
		return false;
	}*/

	//[Confirms]
	public void shipCoords (int startX, int startY, int endX, int endY) {
		// Get necessary coordinates and add them to coordinates array
		coordinates[0] = startX;
		coordinates[1] = startY;
		coordinates[2] = endX;
		coordinates[3] = endX;
		/*int[][] coords = new int[shipSize][2];
		//System.out.println(coords); - SysPrint = Error Checking
		System.out.println(shipSize);
		System.out.println("Ckpt1");
		if (startX == endX || startY == endY){ //Make sure the thing isnt diagonal
			//System.out.println("Ckpt2");
			if ((startX == endX) && (Math.abs(startY - endY) + 1 == shipSize)){ // Confirming Ship Size
				//System.out.println("Ckpt3");
				for (int i = 0; i < shipSize; i++){ // Determining Ship Coordinates
					coords[i][0] = startX;
					System.out.println(coords[i][0]);
					coords[i][1] = startY + i;
					System.out.println(coords[i][1]);
				}
			}
			else if ((startY == endY) && (Math.abs(startX - endX) + 1 == shipSize)){
				//System.out.println("Ckpt3");
				for (int i = 0; i < shipSize; i++){
					coords[i][0] = startX + i;
					System.out.println(coords[i][0]);
					coords[i][1] = startY;
					System.out.println(coords[i][1]);
				}
			}
		}
		Coordinates = coords;
		return coords;*/


	}


	public void aiCoords(){ // will control the coordinates for the computer; split graph into 4 sections | Randomly places ship for AI

		// Randomly generate a start coordinate
		int x = (int)(Math.random()*10+1);
		int y = (int)(Math.random()*10+1);
		int horiVerti = (int)(Math.random()*2); // randomly figure out if going to be vertical or horizontal ship

		coordinates[0] = x; // update values of AI coordinates for constructor access
		coordinates[1] = y; // update values of AI coordinates for constructor access

		if (horiVerti == 0){ //Horizontal placment

			coordinates[3] = y;
			if (x <= 5){ // 'splits' board in half vertically
				coordinates[2] = x+shipSize-1;
			}
			else {
				coordinates[2] = x-shipSize+1;
			}

		}
		else if (horiVerti == 1){ //Vertical placement

			coordinates[2] = x;
			if (y <= 5){ // 'splits" board in half horizontally
				coordinates[3] = y+shipSize-1;
			}
			else {
				coordinates[3] = y-shipSize+1;
			}

		}
		//System.out.println("(" + Coordinates[0] + ", " + Coordinates[1] + ", " + Coordinates[2] + ", " + Coordinates[3] + ")");

	}



	public boolean returnHit(int x, int y){ //returns if ship was hit

		if (coordinates[0] == coordinates[2]){ //X's are the same
			if (x == coordinates[0]){ //If x val = xVal of Coordinates
				if (coordinates[1] < coordinates[3]){
					for (int i = coordinates[1]; i < (coordinates[3] + 1); i++){ //iterating over possible y vals
						if (y == i){
							return true;
						}
					}
				}
				else if (coordinates[1] > coordinates[3]){
					for (int i = coordinates[3]; i < coordinates[1] + 1; i++){
						if (y == i){
							return true;
						}
					}
				}
			}
		}
		else if (coordinates[1] == coordinates[3]){ //Y's are the same
			if (y == coordinates[1]){ //If y val = yVal of Coordinates
				if (coordinates[0] < coordinates[2]){
					for (int i = coordinates[0]; i < (coordinates[2] + 1); i++){
						if (x == i){
							return true;
						}
					}
				}
				else if (coordinates[0] > coordinates[2]){
					for (int i = coordinates[2]; i < (coordinates[0] + 1); i++){
						if (x == i){
							return true;
						}
					}
				}
			}
		}
		return false;

	}


	public boolean toBig (int startX, int startY, int endX, int endY, int size){ //checker func -> if ship too big
			int temp;
		//System.out.println((Math.abs(endX - startX) + 1));
		if (startX == endX){
			if (endY < startY){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

				// if end is smaller than start, switch value of variables
				temp = endY;
				endY = startY;
				startY = temp;
			}

			if ((Math.abs(endY - startY)+1) != size){
				//System.out.println((Math.abs(endY - startY)+1));
				return true;
			}
		}

		if (startY == endY){
			if (endX < startX){ // for loop only starts if end is bigger than y, but player may not enter coordinates like in that manner

				// if end is smaller than start, switch value of variables
				temp = endY;
				endY = startY;
				startY = temp;
			}

			if ((Math.abs(endX - startX) + 1) != size){
				//System.out.println((Math.abs(endX - startX) + 1));
				return true;
			}
		}

		return false;

	}

	public boolean isDiagonal(int startX, int startY, int endX, int endY){ //checker func -> if ship diagonal

		if ((startX == endX) || startY == endY){
			diagonal = false;
			return false;
		}
		diagonal = true;
		return true;
	}

	public boolean onBoard (int startX, int startY, int endX, int endY){ // make sure coordinates are on board to ensure game will work
		if (startX > 10 || startX < 1){ // board is 10 x 10 so need to check with 10 and 1
			inBounds = false;
			return false;
		}
		if (startY > 10 || startY < 1){ // board is 10 x 10 so need to check with 10 and 1
			inBounds = false;
			return false;
		}
		if (endX > 10 || endX < 1){ // board is 10 x 10 so need to check with 10 and 1
			inBounds = false;
			return false;
		}
		if (endY > 10 || endY < 1){ // board is 10 x 10 so need to check with 10 and 1
			inBounds = false;
			return false;
		}
		inBounds = true;
		return true;

	}







}
