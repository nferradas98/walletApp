package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));
            Scene logInScene = new Scene(root);
            logInScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            loginStage.setScene(logInScene);
            loginStage.setResizable(false);
            loginStage.setTitle("Tech Wallet");
            loginStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
