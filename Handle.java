package project;

import java.util.List;

public class Handle{

	public void newUser(int id, String first, String last, String username,
						String password, String pin) {
		User newUser = new User();
		
		newUser.setId(id);
		newUser.setName(first);
		newUser.setLastName(last);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setPin(pin);
		
		//Update database with new User
		
	}
	
	public void logIn(int id, String pin) {
		//get User from databse using id
		User currentUser = new User(); //istead of this
		
		if(pin = currentUser.getPin()) {
			//sucessfully logged in whta next?
		}
		
	}
	
	public void newAccount(User user, String type, double balance) {
		//Creating a new account
		Account newAcc = new Account();
		newAcc.setType(type);
		newAcc.setBalance(balance);
		
		//Adding new account to users list
		List<Account> newList = user.getAccounts();
		newList.add(newAcc);
		user.setAccounts(newList);
		
		//Update database
	}
}
