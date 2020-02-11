package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import project.Account;
import project.User;
import sample.data.Datasource;

public class ControllerStatement {

    @FXML
    public Label user;
    @FXML
    public Label accountNumber;
    @FXML
    public Label totalExpenses;
    @FXML
    public Label totalMoneySaved;
    @FXML
    public Label finalBalance;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox;

    private User user(){
        return Datasource.getInstance().user;
    }

    private Account account(){
        return Datasource.getInstance().account;
    }

    public void initialize() {
        populateTable(account());
    }

    private void populateTable(Account account){

    }
}
