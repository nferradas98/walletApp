package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerLogin {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private TextField newUserName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField passwordConfirmation;
    @FXML
    private GridPane loginStage;
    @FXML
    private Text actionWarningLogIn;
    @FXML
    private Text actionWarningRegister;

    public void initialize() {

    }

    @FXML
    protected void handleLogin() throws Exception {
        if (userName.getText().equals("a") && password.getText().equals("a")) {

            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            mainStage.setTitle("Tech Wallet - Main");
            loginStage.getScene().getWindow().hide();
            mainStage.show();

        } else {
            actionWarningLogIn.setText("Invalid user!");
        }
    }

    @FXML
    protected void handleRegistration() throws Exception {
        if(newUserName.getText().isEmpty() || newPassword.getText().isEmpty() || passwordConfirmation.getText().isEmpty()) {
            actionWarningRegister.setText("All fields must be fulfilled!");
        } else if(!(newPassword.getText().equals(passwordConfirmation.getText()))) {
            actionWarningRegister.setText("Passwords must match!");
        } else {

            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            mainStage.setTitle("Tech Wallet - Main");
            loginStage.getScene().getWindow().hide();
            mainStage.show();
        }
    }
}
