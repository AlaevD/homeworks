package spbu.sem2.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Controller {
    /** random number generator */
    private Random rng = new Random();

    /** game button */
    @FXML
    private Button button;

    /** game field */
    @FXML
    private Pane field;

    /** processes game action.
     * When mouse enters button, new coordinates are generated
     */
    public void process() {
        button.setLayoutX(Math.abs(rng.nextInt()) % field.getWidth());
        button.setLayoutY(Math.abs(rng.nextInt()) % field.getHeight());
    }
}
