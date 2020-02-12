package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerDeposit {

    @FXML
    public TextField amountDeposit;

    public void initialize() {

    }
    
    public boolean deposit(){
        //Setting transaction details
		deposit.setType("Deposit");
		deposit.setAccount(account());
		deposit.setAmount(amountDeposit);
		//Does the user enter details?
		deposit.setDetails("Deposited $" + amount);
		//Any of yall know hot to get it as a string i can get it in type 'Date' that can
		//be printed but not passed as a String
		deposit.setDate("2/8/20");
	    //Date date = Calendar.getInstance.getTime();
        
        return Datasource.getInstance.saveTransaction(deposit);
    }
    
    private Account account(){
        return Datasource.getInstance.account();
}
