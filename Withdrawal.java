package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Withdrawal {
	
	//Withdrawing 
	private boolean withdrawl(String accountNumber, double amount) {
		Transaction withdrawl = new Transaction();
		
		//Setting withdrawl transaction details
		withdrawl.setType("Withdrawl");
		withdrawl.setAccount(accountNumber);
		withdrawl.setAmount(0 - amount);
		//Does user set the details?
		withdrawl.setDetails("Withdrew $" + amount);
		//Any of yall know hot to get it as a string i can get it in type 'Date' that can
		//be printed but not passed as a String
		withdrawl.setDate("2/8/20");
		//Does the user enter details?

		
		//Updating Database
		if(Datasource.getInstance.saveTransaction(withdrawl)) {
			return true;
		}
	}
}
