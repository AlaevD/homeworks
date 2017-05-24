package spbu.sem2.hw5.task2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 280, 310));
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(355);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
