public class Tester{ //Tester Class - To test Methods/Code

	public static void main(String[] args){
		
		Ship test = new Ship(3);
		int[][] hi = test.shipCoords(1, 2, 1, 4);
		for (int i = 0; i < 3; i++){
			
			for (int j = 0; j < 2; j++){
				
				System.out.print(hi[i][j]);
				
			}
			System.out.println();
			
		}
		
	}


}
