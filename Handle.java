package project;

import java.util.List;

public class Handle{

	//Need to add Income
	public boolean newUser(int id, String first, String last, String username,
						String password, String pin) {
		User newUser = new User();
		
		newUser.setId(id);
		newUser.setName(first);
		newUser.setLastName(last);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setPin(pin);
		//newuser.setIncome(income)
		
		//Update database with new User
		Datasource.getInstance.createUser(newUser);
		
		return true;
		
	}
	
	/*------------------------------------------------------------------
	 * Don't know what to do
	 * -----------------------------------------------------------------
	 */
	public User logIn(String userName, String password) {
		//get User from databse?
		 User currentUser = Datasource.getInstance.getUser();		
		
		//Checking that pin matches
		if(password == currentUser.getPassword()) {
			//sucessfully logged in what next?
			
			return currentUser;
		}
		
	}
	
	public boolean newAccount(User user, String type, double balance) {
		//Creating a new account
		Account newAcc = new Account();
		newAcc.setType(type);
		newAcc.setBalance(balance);
		
		//Adding new account to users list
		List<Account> newList = user.getAccounts();
		newList.add(newAcc);
		user.setAccounts(newList);
		
		//Update database
		//How to add a account to user.setAccount
		if(Datasource.getInstance.createAccount(newAcc)) {
			return true;
		}
	}
}
