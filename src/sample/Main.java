package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent calc = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        Scene sceneCalc = new Scene(calc);
        stage.setScene(sceneCalc);

        stage.show();

    }
}
