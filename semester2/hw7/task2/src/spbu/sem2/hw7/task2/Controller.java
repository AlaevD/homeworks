package spbu.sem2.hw7.task2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /** tic-tac-toe game object */
    private TicTacToe game = new TicTacToe();

    /** game size */
    private int size = TicTacToe.getGameSize();

    /** game buttons */
    @FXML
    private ArrayList<Button> buttons;
    
    /** displays messages, e.g. game result */
    @FXML
    private Label display;

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
        Button curButton = buttons.get(index);
        int curI = index / size;
        int curJ = index % size;
        curButton.setText(String.valueOf(game.makeTurn(curI, curJ)));
        curButton.setDisable(true);
        checkForGameEnd();
    }
    
    /** checks if game is finished */
    private void checkForGameEnd() {
        if (game.gameOver()) {
            endGame(game.getGameResult());
        }
    }

    /**
     * shows message to player and ends game by disabling all remaining buttons.
     * @param message message to be shown
     */
    private void endGame(String message) {
        buttons.forEach(b -> b.setDisable(true));
        display.setText(message);
    }

    /** resets game state to the beginning */
    public void resetState() {
        game = new TicTacToe();
        display.setText("");
        for (Button button : buttons) {
            button.setDisable(false);
            button.setText("");
        }
    }
}
