package project;

import java.util.List;

public class Transfer {
	
	//Transfering
	private void transfer(String from, String to, double amount ) {
		
		Transaction transfer = new Transaction();
		
		transfer.setType("Transfer");
		transfer.setAccount(from);
		transfer.setReceivingAccount(to);
		transfer.setAmount(amount);
		//Does the user enter details?
		transfer.setDetails("Transfer of $" + amount + " from " + from + " to " + to);
		//Any of yall know hot to get it as a string i can get it in type 'Date' that can
		//be printed but not passed as a String
		transfer.setDate("2-11-2020");
	
		Datasource.getInstance.saveTransaction(transfer);
	}
	
}
