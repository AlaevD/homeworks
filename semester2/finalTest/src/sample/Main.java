package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private static final int N = 4;
    private static final int maxNumber = N * N / 2;

    private Button[][] buttons = new Button[N][N];
    private int[][] buttonNumbers = new int[N][N];
    private int[] remainingNumbers = new int[maxNumber];
    private Random rng = new Random();
    private enum State {
        BEGINNING,
        PRESSED_FIRST
    }
    State currentState = State.BEGINNING;
    private boolean gameOver = false;
    private int firstButtonI;
    private int firstButtonJ;

    private void initialize() {
        for (int i = 0; i < maxNumber; i++) {
            remainingNumbers[i] = 2;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j] = new Button();
            }
        }
        GridPane root = new GridPane();
        primaryStage.setTitle("Game");

        ColumnConstraints c = new ColumnConstraints();
        RowConstraints r = new RowConstraints();
        r.setPrefHeight(25);
        c.setPrefWidth(25);
        root.getColumnConstraints().add(c);
        root.getRowConstraints().add(r);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                root.add(buttons[i][j], i, j);
                buttonNumbers[i][j] = getNextNumber();
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        process(finalI, finalJ);
                    }
                });
            }
        }



        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    private void process(int i, int j) {
        switch (currentState) {
            case BEGINNING:
                buttons[i][j].setText(String.valueOf(buttonNumbers[i][j]));
                currentState = State.PRESSED_FIRST;
                firstButtonI = i;
                firstButtonJ = j;
                buttons[i][j].setDisable(true);
                break;
            case PRESSED_FIRST:
                buttons[i][j].setText(String.valueOf(buttonNumbers[i][j]));
                if (sameValues(i, j, firstButtonI, firstButtonJ)) {
                    buttons[i][j].setDisable(true);
                } else {
                    clearButton(i, j);
                    clearButton(firstButtonI, firstButtonJ);
                    buttons[i][j].setDisable(false);
                    buttons[firstButtonI][firstButtonJ].setDisable(false);
                }
                currentState = State.BEGINNING;
                break;
        }
    }

    private boolean sameValues(int i, int j, int firstButtonI, int firstButtonJ) {
        return buttonNumbers[i][j] == buttonNumbers[firstButtonI][firstButtonJ];
    }

    private void clearButton(int i, int j) {
        buttons[i][j].setText("");
    }

    private int getNextNumber() {
        int result = (rng.nextInt() % (maxNumber) + maxNumber) % maxNumber;
        while (remainingNumbers[result] == 0) {
            result = (rng.nextInt() % (maxNumber) + maxNumber) % maxNumber;
        }
        remainingNumbers[result]--;
        return result;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
