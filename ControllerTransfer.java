package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerTransfer {
    @FXML
    public TextField fromAccountName;
    @FXML
    public TextField toAccountName;
    @FXML
    public TextField amountTransfer;
    @FXML
    public Label originAccountUpdatedBalance;
    @FXML
    public Label recipientAccountUpdatedBalance;

    public void initialize() {

    }
}
