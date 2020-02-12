package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Deposit {
	
	public boolean deposit(String accountNumber, double amount) {
		Transaction deposit = new Transaction();
		
		//Setting transaction details\
		//Recieving account correct?
		deposit.setType("Deposit");
		deposit.setAccount(accountNumber);
		deposit.setAmount(amount);
		//Does the user enter details?
		deposit.setDetails("Deposited $" + amount);
		//Any of yall know hot to get it as a string i can get it in type 'Date' that can
		//be printed but not passed as a String
		deposit.setDate("2/8/20");
		
		//Updating Database
		if(Datasource.getInstance.saveTransaction(deposit)) {
			return true;
		}
		
		
		
	}
}
