package main;

public class manualTester {
	public static void main(String[] args) {
//		int[][] arr = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
//				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, 
//				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
//				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
//				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 }, 
//				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 },
//				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
//				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 }, 
//				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
//		SudokuSolver ss = new SudokuSolver(arr);
		SudokuSolver ss1 = new SudokuSolver();
		ss1.setGridValue(0, 0, 5);
		ss1.setGridValue(0, 1, 5);
		ss1.solved();
		ss1.getGridValue(1, 10);
		System.out.print(ss1);
	}
}
