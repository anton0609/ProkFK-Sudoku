package main;

public class SudokuSolver {
	private int[][] sudokuGrid;

	/**
	 * Creates a 9x9 sudoku filled with zeros.
	 */
	public SudokuSolver() {
		sudokuGrid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudokuGrid[i][j] = 0;
			}
		}
	}

	/**
	 * Sets the given sudoku to the internal sudoku.
	 * 
	 * @param sudoku
	 *            a 9x9 int matrix with single digits ranging from 0-9.
	 * 
	 * @throws IllegalArgumentException
	 *             if the sudoku is null, has the wrong dimensions or if the
	 *             ints in the sudoku is not between 0 and 9
	 * 
	 */
	public SudokuSolver(int[][] sudoku) {

		if (sudoku == null) {
			throw (new IllegalArgumentException("Null is not a valid argument"));
		}
		if (sudoku.length != 9 || sudoku[0].length != 9) {
			throw (new IllegalArgumentException("the matrix must be 9x9"));
		}
		for (int[] i : sudoku) {
			for (int j : i) {
				if (j < 0 || 9 < j) {
					throw (new IllegalArgumentException("the value at " + i + ":" + j + " is not valid"));
				}
			}
		}

		sudokuGrid = sudoku;
	}

	/**
	 * Solves the sudoku contained in the SudokuSolver object.
	 * 
	 * @return true if the sudoku is solvable, otherwise false;
	 */
	public boolean solved() {
		if (initialCheck()) {
			return solve(0, 0);
		} else {
			return false;
		}

	}

	/**
	 * recursive method to solve the sudoku.
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return true if the current number follows the sudoku ruleset. otherwise
	 *         false;
	 */
	private boolean solve(int r, int c) {
		if (r > 8) {
			c++;
			r = 0;
			if (c > 8) {
				return true;
			}
		}

		if (sudokuGrid[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				boolean works = followsRules(r, c, i);

				if (!works) {
					continue;
				} else {
					sudokuGrid[r][c] = i;
				}
				if (!solve(r + 1, c)) {
					sudokuGrid[r][c] = 0;
					continue;
				} else {
					return true;
				}

			}

		} else {
			return solve(r + 1, c);

		}
		return false;
	}

	private boolean followsRules(int row, int column, int value) {

		// finns "value" i samma kolumn / rad?
		for (int j = 0; j < 9; j++) {
			if (sudokuGrid[row][j] == value || sudokuGrid[j][column] == value) {
				return false;
			}
		}
		// finns value i samma ruta?
		for (int j = row - row % 3; j < row - row % 3 + 3; j++) {
			for (int k = column - column % 3; k < column - column % 3 + 3; k++) {
				if (sudokuGrid[j][k] == value) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean initialCheck() {

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (sudokuGrid[r][c] != 0) {

					for (int j = 0; j < 9; j++) {
						if ((sudokuGrid[j][c] == sudokuGrid[r][c] && j != r)
								|| (sudokuGrid[r][j] == sudokuGrid[r][c] && j != c)) {
							return false;
						}
					}
					for (int j = r - r % 3; j < r - r % 3 + 3; j++) {
						for (int k = c - c % 3; k < c - c % 3 + 3; k++) {
							if (sudokuGrid[j][k] == sudokuGrid[r][c] && j != r && k != c) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Gets the value in the sudoku at row r, column c.
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return the value of the square at row r, column c
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the valuf of r and/or c is not between 0 and 8
	 */
	public int getGridValue(int r, int c) {
		if (0 <= r && r <= 8 && 0 <= c && c <= 8) {
			return sudokuGrid[r][c];
		} else {
			throw (new IndexOutOfBoundsException("at row " + r + ", column " + c));
		}
	}

	/**
	 * Sets the value of the square at row r, column c to "value".
	 * 
	 * @param r
	 *            row, integer between 0-8
	 * @param c
	 *            column, integer between 0-8
	 * @param value
	 *            Integer between 0-9
	 * 
	 * @throws IllegalArgumentException
	 *             if value is not between 0 and 9
	 * 
	 */
	public void setGridValue(int r, int c, int value) {
		if (0 <= value && value <= 9 && 0 <= r && r <= 8 && 0 <= c && c <= 8) {
			sudokuGrid[r][c] = value;
		} else {
			throw (new IllegalArgumentException("the value must be between 0 and 9"));
		}
	}

	/**
	 * Creates and returns a String representing the sudoku.
	 * 
	 * @return a String representing the sudoku.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sudokuGrid.length; i++) {
			sb.append("[");
			for (int j = 0; j < sudokuGrid.length; j++) {
				sb.append(sudokuGrid[i][j] + ", ");
			}
			sb.append("]" + "\n");
		}
		return sb.toString();
	}

}
