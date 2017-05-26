package spbu.sem2.test1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /** random number generator */
    private Random rng = new Random();

    /** game button */
    @FXML
    private Button button;

    /** game field */
    @FXML
    private Pane field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnMouseEntered(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                double newX = Math.abs(rng.nextInt()) % field.getWidth();
                double newY = Math.abs(rng.nextInt()) % field.getHeight();
                while (equal(x, newX) || equal(y, newY) || !visible(newX, newY)) {
                    newX = Math.abs(rng.nextInt()) % field.getWidth();
                    newY = Math.abs(rng.nextInt()) % field.getHeight();
                }
                button.setLayoutY(newY);
                button.setLayoutX(newX);
            }

            private boolean visible(double newX, double newY) {
                return newX + button.getWidth() <= field.getWidth() &&
                       newY + button.getHeight() <= field.getHeight();
            }

            private boolean equal(double x, double y) {
                return Math.abs(x - y) < Math.max(button.getWidth(), button.getHeight());
            }
        });
    }
}
