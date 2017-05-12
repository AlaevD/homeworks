package spbu.sem2.hw5.task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        primaryStage.setTitle("Simple calculator");
        primaryStage.setScene(new Scene(root, 1000, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
