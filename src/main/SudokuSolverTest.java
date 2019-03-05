package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuSolverTest {
	private SudokuSolver sudoku;

	@Before
	public void setUp() throws Exception {
		this.sudoku = new SudokuSolver();
	}

	@After
	public void tearDown() throws Exception {
		this.sudoku = null;
	}

	@Test
	public void testEmptyGrid() {
		assertTrue(sudoku.solved());
	}

	@Test
	public void testSolveableSudoku() {
		int[][] arr = { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 }, { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 }, { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoku.setGridValue(r, c, arr[r][c]);
			}
		}
		assertTrue(sudoku.solved());
	}

	@Test
	public void testInsolvableSudoku1() {

		sudoku.setGridValue(0, 0, 5);
		sudoku.setGridValue(0, 1, 5);
		assertFalse(sudoku.solved());

	}

	@Test
	public void testInsolvableSudoku2() {
		sudoku.setGridValue(0, 0, 1);
		sudoku.setGridValue(0, 1, 2);
		sudoku.setGridValue(0, 2, 3);
		sudoku.setGridValue(1, 0, 4);
		sudoku.setGridValue(1, 1, 5);
		sudoku.setGridValue(1, 2, 6);
		sudoku.setGridValue(2, 3, 7);
		assertFalse(sudoku.solved());
	}

}
