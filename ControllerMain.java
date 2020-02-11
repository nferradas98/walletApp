package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.data.Datasource;


public class ControllerMain {

    @FXML
    private Label balance;
    @FXML
    private Label savings;
    @FXML
    private BorderPane mainStage;
    @FXML
    private TableView<MainTable> table;

    private void listTransactions(){
        Task<ObservableList<MainTable>> task = new GetAllTransactions();
        table.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    public void initialize() {
        listTransactions();
    }

    @FXML
    public void handleStatement() throws Exception{

        Dialog<ButtonType> dialogStatement = new Dialog<>();
        dialogStatement.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Statement.fxml"));
        dialogStatement.setTitle("Tech Wallet - Statement");
        try{
            dialogStatement.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
            return;
        }
        ButtonType buttonExport = new ButtonType("Export");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogStatement.getDialogPane().getButtonTypes().addAll(buttonExport, buttonCancel);
        dialogStatement.getDialogPane().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogStatement.showAndWait();
    }

    public void handleWithdraw() throws Exception {

        Dialog<ButtonType> dialogWithdraw = new Dialog<>();
        dialogWithdraw.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Withdraw.fxml"));
        dialogWithdraw.setTitle("Tech Wallet - Withdraw");
        try{
            dialogWithdraw.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogWithdraw.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogWithdraw.showAndWait();
    }

    public void handleTransfer() throws Exception{

        Dialog<ButtonType> dialogTransfer = new Dialog<>();
        dialogTransfer.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Transfer.fxml"));
        dialogTransfer.setTitle("Tech Wallet - Transfer");
        try{
            dialogTransfer.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogTransfer.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogTransfer.showAndWait();
    }

    public void handleDeposit() throws Exception{

        Dialog<ButtonType> dialogDeposit = new Dialog<>();
        dialogDeposit.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Deposit.fxml"));
        dialogDeposit.setTitle("Tech Wallet - Deposit");
        try{
            dialogDeposit.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogDeposit.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogDeposit.showAndWait();
    }

    public void handleAddExpense() throws Exception{

        Dialog<ButtonType> dialogAddExpense = new Dialog<>();
        dialogAddExpense.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Expense.fxml"));
        dialogAddExpense.setTitle("Tech Wallet - Add Expense");
        try{
            dialogAddExpense.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogAddExpense.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogAddExpense.showAndWait();
    }

    public void handleManageAccount() throws Exception{

        Dialog<ButtonType> dialogManageAccount = new Dialog<>();
        dialogManageAccount.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ManageAccount.fxml"));
        dialogManageAccount.setTitle("Tech Wallet - Accounts");
        try{
            dialogManageAccount.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogManageAccount.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogManageAccount.showAndWait();
    }

    public void handleSettings() throws Exception{

        Dialog<ButtonType> dialogSettings = new Dialog<>();
        dialogSettings.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
        dialogSettings.setTitle("Tech Wallet - Settings");
        try{
            dialogSettings.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        ButtonType buttonConfirm = new ButtonType("Confirm");
        ButtonType buttonCancel = new ButtonType("Cancel");
        dialogSettings.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
        dialogSettings.showAndWait();
    }
}

class GetAllTransactions extends Task {
    @Override
    public ObservableList<MainTable> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryAllTransactions());
    }
}
