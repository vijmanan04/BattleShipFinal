/*Test file for building and running your program - the main is to support
*   development in Repl.it
*
*/

/*
Name: Manan Vij and Kyler Pang
File: Battle.java
Purpose: Start the game and handle User Interface


*/
import java.util.*;

public class Battle {


public static void main ( String[] args) {
	Board playerShips = new Board(); // load in player board with ships
	Board playerHits = new Board(); // load player attack board
	Board computerShips = new Board(); // load computer board with ships

	Scanner scan = new Scanner(System.in);  // scaner

	String colNamesString = " ABCDEFGHIJ  "; // Name of columns on board to use when player enters in attack coordinates
	String rowNamesString = "0123456789 "; // Name of row on board to use when player enters in attack coordinates



	// Introduction to game
	System.out.println("");
	System.out.println("                                 # #  ( )");
  	System.out.println("                              ___#_#___|__");
  	System.out.println("                             _  |____________|  _");
  	System.out.println("                      _=====| | |            | | |==== _");
  	System.out.println("                =====| |.---------------------------. | |====");
  	System.out.println("  <--------------------'   .  .  .  .  .  .  .  .   '--------------|");
  	System.out.println("     |                                                            |");
  	System.out.println("     |______________________________________________MVKP_________|");
	//ASCII Art: https://ascii.co.uk/art/battleship (this is the refernce from where this is modified

	// Add spacing
	System.out.println("");
	System.out.println("");
	System.out.println("");
	System.out.println("");
	// Menu
	System.out.println("WELCOME TO BATTLESHIP!");
	System.out.println("Press Enter key to start game...");
	scan.nextLine();
	//Instructions
	System.out.println("INSTRUCTIONS");
	System.out.println("Welcome to BattleShip! Your goal is to sink the computer's ships,\nbefore the computer sinks yours. You each have 3 ships to place\non a 10 x 10 board.");
	System.out.println("Press Enter key to continue...");
	scan.nextLine();
	System.out.println("USER INTERFACE");
	System.out.println("When you play, you can only see your own board. Your board consists\nof two parts that are separated by dashes (-) and pluses (+).\nThis boundary seperates your ATTACKING board (top) from your\nSTRATEGIC board (bottom). That ATTACKING board is where you \nwill be able to see your hits and misses againt the computer.\nThe STRATEGIC board is where you can see your ships and the\ncomputer's hits and misses, marked by H and M respectively.\n");
	// Show a picture of an empty board to intialize the reader
	System.out.println("Here is a picture of an empty board: \n");
	playerHits.showBoard();
	System.out.println("   +--+--+--+--+--+--+--+--+--+ ");
	playerShips.showBoard();
	System.out.println("Press Enter key to continue...");
	scan.nextLine();
	System.out.println();
	System.out.println("HOW TO PLACE SHIPS");
	System.out.println("When prompted, enter the letter and integer coordinates for \nBOTH ENDS of your ships on the STRATEGIC board. You can not\nplace ships diagnonally nor can you overlap any ships. Also,\nmake sure that each ship is plaed within the board. ");
	System.out.println("Press Enter key to continue...");
	scan.nextLine();
	System.out.println("ATTACKING");
	System.out.println("When prompted, enter the letter and integer coordinates of the\nlocation on your ATTACKING board. Hits will be marked with an @\nand misses with an X.");
	System.out.println("Press Enter to start playing.");
	scan.nextLine();


	//Make ships
	System.out.println("You will have 3 ships that you can place, of sizes 2, 3, and 4.\n\nGenerating ships ... ");
	//Create 3 instances of ships
	Ship S2 = new Ship();
	Ship S3 = new Ship(3);
	Ship S4 = new Ship(4);
	Ship ships[] = {S2, S3, S4}; // put all ships into array to loop through more easily

	//need to convert from string to int -> Player Ship Placement Variables; the variables below are the string version
	String xStartStringPlayer;
	String yStartStringPlayer;
	String xEndStringPlayer;
	String yEndStringPlayer;


	// these are the integers generated by mapping strings to an array
	int xStartPlayer;
	int yStartPlayer;
	int xEndPlayer;
	int yEndPlayer;

	//check if ship placement is fine; booleans represnt checks
	boolean boardChecker;
	boolean tooBig;
	boolean isDiagonal;
	boolean doesOverlap;
	// array for coordinates (of ship)
	int[] coord = new int[4];

	//get ship coordinates for each ship
	for (int i = 0; i < 3; i++) { // loop through ships
		// prompt user
		System.out.println("");
		System.out.println("Place your ship with length " + (i + 2) + ". You will need to place the specify the start end end coordinates of the Ship" );
		System.out.print("Enter an uppercase letter for the column of the start of the ship: ");
		yStartStringPlayer = (scan.nextLine()).toUpperCase(); // make sure input is all uppercase
		System.out.print("Enter a row coordinate as an integer for the start: ");
		xStartStringPlayer = (scan.nextLine()).toUpperCase();

		yStartPlayer = (colNamesString).indexOf(yStartStringPlayer); // find the index of the string which is matched in the initial mapper string
		xStartPlayer = (rowNamesString).indexOf(xStartStringPlayer); // same as above

		System.out.print("Enter an uppercase letter for the column of the end of the ship: ");
		yEndStringPlayer = (scan.nextLine()).toUpperCase(); // make sure input is upper case
		System.out.print("Enter a row coordinate as an integer for the end: ");
		xEndStringPlayer = (scan.nextLine()).toUpperCase();

		yEndPlayer = (colNamesString).indexOf(yEndStringPlayer); // find the index of the string which is matched in the initial mapper string
		xEndPlayer = (rowNamesString).indexOf(xEndStringPlayer); // same as above

		// checking
		boardChecker = ships[i].onBoard(xStartPlayer+1, yStartPlayer, xEndPlayer+1, yEndPlayer); //Check if ship is on board
		tooBig = ships[i].toBig(xStartPlayer+1, yStartPlayer, xEndPlayer+1, yEndPlayer, i+2); //Check if ship is tooBig
		isDiagonal = ships[i].isDiagonal(xStartPlayer+1, yStartPlayer, xEndPlayer+1, yEndPlayer); //Check if ship is diagonal
		doesOverlap = playerShips.doesOverlap(xStartPlayer+1, yStartPlayer, xEndPlayer+1, yEndPlayer); //Checks if ships overlap

		if (!boardChecker){ //Using the vars boardChecker, tooBig, isDiagonal, doesOverlap, next few if statements determine if we need to replace any ships
			System.out.println("Ship is outside the board. Place your ship again.");
			i--;
			continue;
		}
		//System.out.println(xEndPlayer+1);
		if (tooBig){ // check if ship is to big
			System.out.println("Ship is too big. You need to place a ship of size " + (i +2)+ ".");
			i--;
		}
		if (isDiagonal){ // check if ship is diangonally placed
			System.out.println("Ship is diagonal. Place your ship again.");
			i--;
			continue;
		}
		if (doesOverlap){ // check if overlaping with other ships
			System.out.println("Ship overlaps another ship. Place your ship again.");
			i--;
			continue;
		}

		if(boardChecker && !tooBig && !isDiagonal && !doesOverlap){ //If all valid, add to board
			coord[0] = xStartPlayer + 1; // put into cooordinate array to pass into function later
			coord[1] = yStartPlayer;
			coord[2] = xEndPlayer+1; //  add one becase need the first row is not used
			coord[3] = yEndPlayer;
			ships[i].shipCoords(xStartPlayer + 1, yStartPlayer, xEndPlayer+1, yEndPlayer); // update ship coordinates
			// display player boards
			playerShips.shipLocation(coord);
			playerHits.showBoard();
			System.out.println("+--+--+--+--+--+--+--+--+--+ ");
			playerShips.showBoard();
		}



	}






	// generate AI ships
	boolean gameOver = false;
	Ship AIS2 = new Ship(); // load AI ships
	Ship AIS3 = new Ship(3);
	Ship AIS4 = new Ship(4);
	Ship computeraiShips[] = {AIS2, AIS3, AIS4}; //Putting Ships into Array for easy access/manipulation

	boolean aiOverlap; //Making sure AI ships dont overlap
	for (int i = 0; i < 3; i++) {
		computeraiShips[i].aiCoords(); // generate AO ship coordinates
		aiOverlap = computerShips.doesOverlap(computeraiShips[i].coordinates[0], computeraiShips[i].coordinates[1], computeraiShips[i].coordinates[2], computeraiShips[i].coordinates[3]); // check ifgenereated computer computer ships overlap
		while (aiOverlap){
			computeraiShips[i].aiCoords(); // if overlap, generate new coordinates nad make i one lower so it can re assign values to ship
		}
		computeraiShips[i].shipCoords(computeraiShips[i].coordinates[0], computeraiShips[i].coordinates[1], computeraiShips[i].coordinates[2], computeraiShips[i].coordinates[3]); // place computer ships on boards

	}

	//Game Variables
	char attackLetter; //Player will enter string for attack column
	int attackNumberCol; //Convert string for attack to integer for indexing
	char attackNumberAsString; //Player will enter string for attack row
	int attackNumberRow; //Convert string for attack to integer for indexing
	boolean playerSucess; // checks if player was sucessful in hitting ship
	boolean computerSucess; // checks if computer was sucessful in hitting ship
	String buffer; //buffer

	int computerAttackX; // computer X attack coordinate
	int computerAttackY; // computer Y attack coordinate
	int playerAttackSucessNumber = 0; // counter for number of hits for player
	int computerAttackSucessNumber = 0; // counter for number of hits for computer


	//Begin game
	while (!gameOver){ // Keep going until game ends
		//Get attack coordinates from player
		System.out.println("Guess where to attack. You will need to enter a letter and an integer coordinate as integers. ");
		System.out.print("Enter an uppercase letter: ");
		attackLetter = (((scan.nextLine())).toUpperCase()).charAt(0); // convert input to uppercase

		attackNumberCol = (colNamesString).indexOf(attackLetter); // Get index of letter so to enter into attack method of Board.java
		System.out.print("Enter a row coordinate as an integer: ");
		attackNumberAsString = (((scan.nextLine())).toUpperCase()).charAt(0); // convert input to uppercase
		attackNumberRow = (rowNamesString).indexOf(attackNumberAsString); // Get index of number (which is a string right now) so to enter into attack method of Board.java

		//Attack computer ships
		System.out.println();
		System.out.print("Firing missles at enemy: ");
		playerSucess = computerShips.registerAttack(attackNumberRow+1, attackNumberCol); // register the players attack on computer; return true if hit
		playerHits.markAttack(attackNumberRow+1, attackNumberCol, playerSucess); // if player hit, update player attack board as needed
		if (playerSucess){
			playerAttackSucessNumber += 1; // increment counter if hit is found
		}
		// Show board
		playerHits.showBoard();
		System.out.println("   +--+--+--+--+--+--+--+--+--+ ");
		playerShips.showBoard();
		System.out.println();
		System.out.println("You fired at " + rowNamesString.substring(attackNumberRow, attackNumberRow+1) + colNamesString.substring(attackNumberCol, attackNumberCol+1)); // write out information for player to see the summary of the round


		//Generate attack coordinates from computer and attack player
		computerAttackX = (int) (Math.random() * 9 + 1); //random number from 1 to 10
		computerAttackY = (int) (Math.random() * 9 + 1); //random number from 1 to 10
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("Enemy firing missles: ");
		computerSucess = playerShips.registerAttack(computerAttackX, computerAttackY); // register attack on player board; mark if computer hits ship
		if (computerSucess){
			computerAttackSucessNumber += 1; // increment counter if hit is found
		}
		// Show board
		playerHits.showBoard();
		System.out.println("   +--+--+--+--+--+--+--+--+--+ ");
		playerShips.showBoard();
		System.out.println();
		System.out.println("Enemy fired at " + rowNamesString.substring(computerAttackX-1, computerAttackX) + colNamesString.substring(computerAttackY, computerAttackY+1)); // write out information for player to see the summary of the round
		System.out.println();
		System.out.println();

		if ((playerAttackSucessNumber == 9) || (computerAttackSucessNumber == 9)){ //Game Over Mechanism, total number of hits should be 2 + 3 + 4
			gameOver = true; // if total number of hits is 9, game is over
		}

	}

}  //close main method

}  //close the Main class
