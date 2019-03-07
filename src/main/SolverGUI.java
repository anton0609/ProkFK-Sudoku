package main;

import java.util.Optional;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class SolverGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		SudokuSolver ss = new SudokuSolver();
		BorderPane root = new BorderPane();
		HBox buttonBox = new HBox(5);
		buttonBox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 280, 260);
		Button clearB = new Button("Clear");
		Button solveB = new Button("Solve");
		HBox.setHgrow(solveB, Priority.NEVER);
		GridPane grid = new GridPane();
		primaryStage.setResizable(false);

		grid.setMaxWidth(220);
		grid.setMinWidth(220);
		for (int i = 0; i < 9; i++) {
			TextField[] textFields = new OneLetterTextField[9];
			for (int j = 0; j < 9; j++) {
				textFields[j] = new OneLetterTextField();
				if (((2 < j && j < 6) && (i < 3 || i > 5)) || (2 < i && i < 6) && (j < 3 || j > 5)) {
					textFields[j].setStyle("-fx-control-inner-background: orange");

				}

			}
			grid.addRow(i, textFields);
		}

		buttonBox.getChildren().addAll(solveB, clearB);
		root.setBottom(buttonBox);
		root.setCenter(grid);

		primaryStage.setTitle("Sudoku Solver");
		primaryStage.setScene(scene);
		primaryStage.show();

		clearB.setOnAction(ActionEvent -> {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure that you want to clear the sudoku? \nThis action is irreversible");
			a.setHeaderText("Confirm Clear");
			Optional<ButtonType> result = a.showAndWait();
			if (!result.isPresent()) {

			} else if (result.get() == ButtonType.OK) {
				for (Node tf : grid.getChildren()) {
					((OneLetterTextField) tf).clear();
				}
			}

		});

		solveB.setOnAction(ActionEvent -> {

			int r = 0;
			int c = 0;
			boolean validInput = true;
			for (Node tf : grid.getChildren()) {
				if (r > 8) {
					c++;
					r = 0;
				}
				((OneLetterTextField) tf).commitValue();
				if (((OneLetterTextField) tf).getText().matches("[0-9]||\\s")) {
					if ((((OneLetterTextField) tf).getText().equals(""))) {
						ss.setGridValue(r, c, 0);
					} else {
						ss.setGridValue(r, c, Integer.parseInt(((OneLetterTextField) tf).getText()));
					}
				} else {
					Alert a = new Alert(Alert.AlertType.ERROR, ("The value at row " + r + ", column " + c
							+ " is invalid \n" + "Please enter a new digit between 1 and 9"));
					a.setHeaderText("Invalid Input!");
					a.showAndWait();
					validInput = false;

					break;
				}
				r++;
			}
			boolean sudokuSolved = ss.solved();
			if (validInput && sudokuSolved) {
				r = 0;
				c = 0;
				for (Node tf : grid.getChildren()) {
					if (r > 8) {
						c++;
						r = 0;
					}
					((OneLetterTextField) tf).setText(Integer.toString(ss.getGridValue(r, c)));
					r++;
				}
			}
			if (!sudokuSolved) {
				Alert a2 = new Alert(Alert.AlertType.ERROR, "The sudoku has no solution");
				a2.setHeaderText("Insolvable sudoku");
				a2.showAndWait();
			} else {
				Alert a3 = new Alert(Alert.AlertType.INFORMATION, "This Sudoku has been solved");
				a3.setHeaderText("Success");
				a3.showAndWait();
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
