package spbu.sem2.hw7.task2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /** game buttons */
    @FXML
    private ArrayList<Button> buttons;
    
    /** display */
    @FXML
    private Label display;
    
    /** Either "X" or "O" depending on current turn */
    private String currentTurn = "X";
    
    /** how many buttons(max possible number) player can press before game ends */
    private int buttonsLeft = 9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < buttons.size(); i++) {
            int finalI = i;
            buttons.get(i).setOnAction(event -> buttonClicked(finalI));
        }
    }

    /**
     * processes clicked game button
     * @param index index of pressed button in buttons array
     */
    private void buttonClicked(int index) {
        display.setText("");
        Button curButton = buttons.get(index);
        curButton.setText(currentTurn);
        curButton.setDisable(true);
        checkForWin();
        buttonsLeft--;
        if (buttonsLeft == 0) {
            endGame("You lost");
            return;
        }
        changeTurn();
    }
    
    /** checks if player have won */
    private void checkForWin() {
        if (gameOver()) {
            endGame("You win");
        }
    }

    /**
     * shows message to player and ends game by disabling all remaining buttons.
     * @param message message to be shown
     */
    private void endGame(String message) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(true);
        }
        display.setText(message);
    }

    /** resets game state to the beginning */
    public void resetState() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setDisable(false);
            buttons.get(i).setText("");
        }
        currentTurn = "X";
        buttonsLeft = 9;
    }

    /** checks if game is over(with player having win) */
    private boolean gameOver() {
        return checkRows() || checkCols() || checkDiagonals();
    }

    /**
     * checks diagonals for three of a kind
     * @return true if at least one diagonal contains not null same values
     */
    private boolean checkDiagonals() {
        String topLeft = buttons.get(0).getText();
        String mid = buttons.get(4).getText();
        String botRight = buttons.get(8).getText();
        boolean cond1 = topLeft.equals(mid) && mid.equals(botRight) && !mid.isEmpty();

        String topRight = buttons.get(2).getText();
        String botLeft = buttons.get(6).getText();
        boolean cond2 = mid.equals(topRight) && topRight.equals(botLeft) && !mid.isEmpty();

        return cond1 || cond2;
    }

    /**
     * checks columns for three of a kind
     * @return true if at least one column contains not null same values
     */
    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            String top = buttons.get(i).getText();
            String mid = buttons.get(i + 3).getText();
            String bot = buttons.get(i + 6).getText();
            if (top.equals(mid) && mid.equals(bot) && !mid.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks rows for three of a kind
     * @return true if at least one row contains not null same values
     */
    private boolean checkRows() {
        for (int i = 0; i < buttons.size(); i += 3) {
            String left = buttons.get(i).getText();
            String mid = buttons.get(i + 1).getText();
            String right = buttons.get(i + 2).getText();
            if (left.equals(mid) && mid.equals(right) && !mid.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /** changes game turn (e.g. "X" becomes "O") */
    private void changeTurn() {
        currentTurn = (currentTurn.equals("X") ? "O" : "X");
    }
}
