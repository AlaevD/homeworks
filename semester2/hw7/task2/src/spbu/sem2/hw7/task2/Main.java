package spbu.sem2.hw7.task2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 470, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
