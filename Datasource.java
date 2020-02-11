package wallet.data;

import project.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Datasource {

    private static final String DB = "wallet_database.db";
    private static final String CONNECTION = "jdbc:sqlite:C:\\Users\\nicol\\OneDrive\\Spring 2020\\Software Engr. II\\" + DB;

    private static Datasource instance = new Datasource();
    private Datasource(){}
    public static Datasource getInstance(){
        return instance;
    }

    //=============================DATABASE TABLES==============================//

    //============TABLE USERS============//
    private static final String TABLE_USERS = "Users";
    private static final String USERS_ID = "id";
    private static final String USERS_NAME = "name";
    private static final String USERS_LAST = "last";
    private static final String USERS_USERNAME = "username";
    private static final String USERS_PASSWORD = "password";
    private static final String USER_PIN = "pin";

    private static final int INDEX_USERS_ID = 1;
    private static final int INDEX_USERS_NAME = 2;
    private static final int INDEX_USERS_LAST = 3;
    private static final int INDEX_USERS_USERNAME = 4;
    private static final int INDEX_USERS_PASSWORD = 5;
    private static final int INDEX_USERS_PIN = 6;


    //=============TABLE ACCOUNTS==========//
    private static final String TABLE_ACCOUNTS = "Accounts";
    private static final String ACCOUNTS_NUMBER = "number";
    private static final String ACCOUNTS_TYPE = "type";
    private static final String ACCOUNTS_BALANCE = "balance";
    private static final String ACCOUNTS_INCOME = "income";
    private static final String ACCOUNTS_INCOME_DAY = "income_day";
    private static final String ACCOUNTS_SAVINGS = "savings";
    private static final String ACCOUNTS_USER = "user";

    private static final int INDEX_ACCOUNTS_NUMBER = 1;
    private static final int INDEX_ACCOUNTS_TYPE = 2;
    private static final int INDEX_ACCOUNTS_BALANCE = 3;
    private static final int INDEX_ACCOUNTS_INCOME = 4;
    private static final int INDEX_ACCOUNTS_INCOME_DAY = 5;
    private static final int INDEX_ACCOUNTS_SAVINGS = 6;
    private static final int INDEX_ACCOUNTS_USER = 7;


    //==============TRANSACTIONS=============//
    private static final String TABLE_TRANSACTIONS = "Transactions";
    private static final String TRANSACTIONS_ID = "id";
    private static final String TRANSACTIONS_ACCOUNT = "account";
    private static final String TRANSACTIONS_TYPE = "type";
    private static final String TRANSACTIONS_AMOUNT = "amount";
    private static final String TRANSACTIONS_DETAILS  = "details";
    private static final String TRANSACTIONS_DATE = "date";

    private static final int INDEX_TRANSACTIONS_ID = 1;
    private static final int INDEX_TRANSACTIONS_ACCOUNT = 2;
    private static final int INDEX_TRANSACTIONS_TYPE = 3;
    private static final int INDEX_TRANSACTIONS_AMOUNT = 4;
    private static final int INDEX_TRANSACTIONS_DETAILS = 5;
    private static final int INDEX_TRANSACTIONS_DATE = 6;


    //=============TABLE SAVINGS===========//
    private static final String TABLE_SAVINGS = "Savings";
    private static final String SAVINGS_USER = "user";
    private static final String SAVINGS_GOAL = "goal";
    private static final String SAVINGS_TOTAL = "total";


    //=============================DATABASE QUERIES=============================//

    //LOGIN//
    private static final String LOGIN = "SELECT * FROM " + TABLE_USERS + " WHERE " + USERS_USERNAME + " = ? AND " +
            USERS_PASSWORD + " = ?";

    //CREATE USER//
    private static final String CREATE_USER = "INSERT INTO " + TABLE_USERS + "(" + USERS_NAME + ", " + USERS_LAST + ", " +
            USERS_USERNAME + ", " + USERS_PASSWORD + ", " + USER_PIN + ") VALUES (?, ?, ?, ?, ?)";
    private static final String CREATE_USER_SAVINGS = "INSERT INTO " + TABLE_SAVINGS + "(" + SAVINGS_USER + ", " + SAVINGS_GOAL + ", " + SAVINGS_TOTAL+ ") VALUES (?, ?, ?)";

    //QUERY INITIAL TABLE//
    private static final String QUERY_ACCOUNTS = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + ACCOUNTS_USER + " = ?";
    private static final String QUERY_TRANSACTIONS = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + TRANSACTIONS_ACCOUNT + " = ?";

    //QUERY TRANSACTION//
    private static final String QUERY_ACCOUNT_BALANCE = "SELECT " + ACCOUNTS_BALANCE + " FROM " + TABLE_ACCOUNTS + " WHERE " + ACCOUNTS_NUMBER + " = ?";
    private static final String QUERY_SINGLE_TRANSACTION = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + TRANSACTIONS_ID + " = ?";

    //SAVE TRANSACTION//
    private static final String SAVE_TRANSACTION = "INSERT INTO " + TABLE_TRANSACTIONS + "(" + TRANSACTIONS_ACCOUNT + ", " +
            TRANSACTIONS_TYPE + ", " + TRANSACTIONS_AMOUNT + ", " + TRANSACTIONS_DETAILS + ", " + TRANSACTIONS_DATE + ") " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_TRANSACTION = "UPDATE " + TABLE_TRANSACTIONS + " SET " + TRANSACTIONS_AMOUNT + " = ? WHERE " + TRANSACTIONS_ID + " = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE " + TABLE_ACCOUNTS + " SET " + ACCOUNTS_BALANCE + " = ? WHERE " + ACCOUNTS_NUMBER + " = ?";

    //SAVINGS//
    private static final String QUERY_SAVINGS = "SELECT * FROM " + TABLE_SAVINGS + " WHERE " + SAVINGS_USER + " = ?";
    private static final String UPDATE_SAVINGS = "UPDATE " + TABLE_SAVINGS + " SET " + SAVINGS_GOAL + " = ? WHERE " + SAVINGS_USER + " = ?";


    //======================DATABASE PREPARED STATEMENTS========================//
    private PreparedStatement login;
    private PreparedStatement createUser;
    private PreparedStatement createUserSavings;

    private PreparedStatement queryAccounts;
    private PreparedStatement queryTransactions;

    private PreparedStatement queryAccountBalance;
    private PreparedStatement querySingleTransaction;

    private PreparedStatement saveTransaction;
    private PreparedStatement editTransaction;
    private PreparedStatement updateAccount;

    private PreparedStatement querySavings;
    private PreparedStatement updateSavings;

    //=============================SERVER VARIABLES=============================//

    private Connection connection;

    //=============================SERVER METHODS===============================//

    public void open(){
        try{
            //=Open connection=//
            connection = DriverManager.getConnection(CONNECTION);

            //=Open prepared statements=//
            login = connection.prepareStatement(LOGIN);
            createUser = connection.prepareStatement(CREATE_USER);
            createUserSavings = connection.prepareStatement(CREATE_USER_SAVINGS);

            queryAccounts = connection.prepareStatement(QUERY_ACCOUNTS);
            queryTransactions = connection.prepareStatement(QUERY_TRANSACTIONS);

            queryAccountBalance = connection.prepareStatement(QUERY_ACCOUNT_BALANCE);
            querySingleTransaction = connection.prepareStatement(QUERY_SINGLE_TRANSACTION);
            saveTransaction = connection.prepareStatement(SAVE_TRANSACTION);
            editTransaction = connection.prepareStatement(EDIT_TRANSACTION);
            updateAccount = connection.prepareStatement(UPDATE_ACCOUNT);

            querySavings = connection.prepareStatement(QUERY_SAVINGS);
            updateSavings = connection.prepareStatement(UPDATE_SAVINGS);
        } catch (SQLException e){
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void close(){
        try{
            //=Close Prepared Statements=//
            if(login != null){
                login.close();
            }
            if(createUser != null){
                createUser.close();
            }
            if(createUserSavings != null){
                createUserSavings.close();
            }

            if(queryAccounts != null){
                queryAccounts.close();
            }
            if(queryTransactions != null){
                queryTransactions.close();
            }

            if(queryAccountBalance != null){
                queryAccountBalance.close();
            }
            if(querySingleTransaction != null){
                querySingleTransaction.close();
            }

            if(saveTransaction != null){
                saveTransaction.close();
            }
            if(editTransaction != null){
                editTransaction.close();
            }
            if(updateAccount != null){
                updateAccount.close();
            }

            if(querySavings != null){
                querySavings.close();
            }
            if(updateSavings != null){
                updateSavings.close();
            }

            //=Close connection=//
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e){
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }

    //====Functionality methods====//

    public User login(String username, String password){
        try{
            login.setString(1, username);
            login.setString(2, password);
            ResultSet result = login.executeQuery();
            if(result.next()){
                User user = new User();
                user.setId(result.getInt(INDEX_USERS_ID));
                user.setName(result.getString(INDEX_USERS_NAME));
                user.setLastName(result.getString(INDEX_USERS_LAST));
                user.setUsername(username);
                user.setPassword(password);
                user.setPin(result.getString(INDEX_USERS_PIN));

                return user;
            }
            return null;
        } catch (SQLException e){
            System.out.println("Error logging in: " + e.getMessage());
            return null;
        }


    }

    public boolean createUser(User newUser){
        try{
            connection.setAutoCommit(false);
            connection.setSavepoint();

            createUser.setString(1, newUser.getName());
            createUser.setString(2, newUser.getLastName());
            createUser.setString(3, newUser.getUsername());
            createUser.setString(4, newUser.getPassword());
            createUser.setString(5, newUser.getPin());
            int inserted = createUser.executeUpdate();
            if(inserted == 1){
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e){
            System.out.println("Error saving new user: " + e.getMessage());
            return false;
        } finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException e){
                System.out.println("Error setting auto commit: " + e.getMessage());
            }
        }
    }

    public List<Account> queryAccountsForTable(int id){
        try {
            List<Account> accounts = new ArrayList<>();
            queryAccounts.setInt(1, id);
            ResultSet result = queryAccounts.executeQuery();
            while(result.next()){
                Account account = new Account();
                account.setAccountNumber(result.getString(INDEX_ACCOUNTS_NUMBER));
                account.setType(result.getString(INDEX_ACCOUNTS_TYPE));
                account.setBalance(result.getDouble(INDEX_ACCOUNTS_BALANCE));
                account.setIncome(result.getDouble(INDEX_ACCOUNTS_INCOME));
                account.setTransactions(null);

                accounts.add(account);
            }

            return accounts;

        } catch (SQLException e){
            System.out.println("Error querying user accounts: " + e.getMessage());
            return null;
        }
    }

    public List<Transaction> queryTransactionsForTable(String account){
        try{

            List<Transaction> transactions = new ArrayList<>();
            queryTransactions.setString(1, account);
            ResultSet result = queryTransactions.executeQuery();
            while (result.next()){
                Transaction transaction = new Transaction();
                transaction.setId(result.getInt(INDEX_TRANSACTIONS_ID));
                transaction.setAccount(result.getString(INDEX_TRANSACTIONS_ACCOUNT));
                transaction.setType(result.getString(INDEX_TRANSACTIONS_TYPE));
                transaction.setAmount(result.getDouble(INDEX_TRANSACTIONS_AMOUNT));
                transaction.setDetails(result.getString(INDEX_TRANSACTIONS_DETAILS));
                transaction.setDate(result.getString(INDEX_TRANSACTIONS_DATE));

                transactions.add(transaction);
            }

            return transactions;

        } catch (SQLException e){
            System.out.println("Error querying transactions for table: " + e.getMessage());
            return null;
        }
    }

    public Transaction queryTransaction(int id){
        try{
            querySingleTransaction.setInt(1, id);
            ResultSet result = querySingleTransaction.executeQuery();
            if(result.next()){
                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setAccount(result.getString(INDEX_TRANSACTIONS_ACCOUNT));
                transaction.setType(result.getString(INDEX_TRANSACTIONS_TYPE));
                transaction.setAmount(result.getDouble(INDEX_TRANSACTIONS_AMOUNT));
                transaction.setDetails(result.getString(INDEX_TRANSACTIONS_DETAILS));
                transaction.setDate(result.getString(INDEX_TRANSACTIONS_DATE));

                return transaction;
            }
            return null;
        } catch (SQLException e){
            System.out.println("Error querying transaction: " + e.getMessage());
            return null;
        }
    }

    public boolean saveTransaction(Transaction transaction){

        try{
            connection.setAutoCommit(false);
            connection.setSavepoint();

            double source = 0;
            double destination = 0;

            //query account balance
            queryAccountBalance.setString(1, transaction.getAccount());
            ResultSet result = queryAccountBalance.executeQuery();
            if(result.next()){
                source += result.getDouble(1);
            }

            //check for the account type
            if(transaction.getType().equals("Withdrawal") || transaction.getType().equals("Expense")){
                updateAccount.setDouble(1, (source - transaction.getAmount()));
                updateAccount.setString(2, transaction.getAccount());
                int update = updateAccount.executeUpdate();
                if(update == 1){
                    saveTransaction.setString(1, transaction.getAccount());
                    saveTransaction.setString(2, transaction.getType());
                    saveTransaction.setDouble(3, transaction.getAmount());
                    saveTransaction.setString(4, transaction.getDetails());
                    saveTransaction.setString(5, transaction.getDate());
                    int inserted = saveTransaction.executeUpdate();
                    if(inserted == 1){
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } else if(transaction.getType().equals("Transfer")) {
                //query Destination account balance
                queryAccountBalance.setString(1, transaction.getReceivingAccount());
                ResultSet resultDestination =queryAccountBalance.executeQuery();
                if(resultDestination.next()){
                    destination += resultDestination.getDouble(1);
                    //update both accounts values
                    updateAccount.setDouble(1, (source - transaction.getAmount()));
                    updateAccount.setString(2, transaction.getAccount());
                    int update = updateAccount.executeUpdate();
                    if(update == 1){
                        updateAccount.setDouble(1, destination + transaction.getAmount());
                        updateAccount.setString(2, transaction.getReceivingAccount());
                        int update2 = updateAccount.executeUpdate();
                        if(update2 == 1){
                            saveTransaction.setString(1, transaction.getAccount());
                            saveTransaction.setString(2, transaction.getType());
                            saveTransaction.setDouble(3, transaction.getAmount());
                            saveTransaction.setString(4, transaction.getDetails());
                            saveTransaction.setString(5, transaction.getDate());
                            int inserted = saveTransaction.executeUpdate();
                            if(inserted == 1){
                                connection.commit();
                                return true;
                            } else {
                                connection.rollback();
                                return false;
                            }
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                updateAccount.setDouble(1, source + transaction.getAmount());
                updateAccount.setString(2, transaction.getAccount());
                int update = updateAccount.executeUpdate();
                if(update == 1){
                    saveTransaction.setString(1, transaction.getAccount());
                    saveTransaction.setString(2, transaction.getType());
                    saveTransaction.setDouble(3, transaction.getAmount());
                    saveTransaction.setString(4, transaction.getDetails());
                    saveTransaction.setString(5, transaction.getDate());
                    int inserted = saveTransaction.executeUpdate();
                    if(inserted == 1){
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e){
            System.out.println("Error saving transaction: " + e.getMessage());
            return false;
        } finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException e){
                System.out.println("Error setting auto commit: " + e.getMessage());
            }
        }
    }

    public boolean editTransaction(Transaction transaction){

        try{
            connection.setAutoCommit(false);
            connection.setSavepoint();

            double startingBalance = 0;
            double originalAmount = 0;
            double newBalance = 0;

            //query balance//
            queryAccountBalance.setString(1, transaction.getAccount());
            ResultSet result = queryAccountBalance.executeQuery();
            if(result.next()){
                startingBalance += result.getDouble(1);
            }

            //query transaction amount//
            querySingleTransaction.setInt(1, transaction.getId());
            ResultSet trans = querySingleTransaction.executeQuery();
            if(result.next()){
                originalAmount += trans.getDouble(INDEX_TRANSACTIONS_AMOUNT);
            }

            editTransaction.setDouble(1, transaction.getAmount());
            editTransaction.setInt(2, transaction.getId());
            int affected = editTransaction.executeUpdate();
            if(affected == 1){
                if(transaction.getType().equals("Withdrawal") || transaction.getType().equals("Expense")){
                    newBalance += (startingBalance + originalAmount - transaction.getAmount());
                } else if(transaction.getType().equals("Deposit")){
                    newBalance += (startingBalance - originalAmount + transaction.getAmount());
                }
                updateAccount.setDouble(1, newBalance);
                updateAccount.setString(2, transaction.getAccount());
                int edited = updateAccount.executeUpdate();
                if(edited == 1){
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e){
            System.out.println("Error editing transaction correctly: " + e.getMessage());
            return false;
        } finally {
            try{
                connection.setAutoCommit(true);
            } catch (SQLException e){
                System.out.println("Error resetting auto commit: " + e.getMessage());
            }
        }
    }

    public List<Transaction> queryStatement(String month, String year, String accountNumber){
        try{

            List<Transaction> transactions = new ArrayList<>();
            queryTransactions.setString(1, accountNumber);
            ResultSet result = queryTransactions.executeQuery();
            while(result.next()){
                String[] date = result.getString(INDEX_TRANSACTIONS_DATE).split("-");
                if(month.equals(date[1]) && year.equals(date[2])){
                    Transaction transaction = new Transaction();
                    transaction.setId(result.getInt(INDEX_TRANSACTIONS_ID));
                    transaction.setAccount(result.getString(INDEX_TRANSACTIONS_ACCOUNT));
                    transaction.setType(result.getString(INDEX_TRANSACTIONS_TYPE));
                    transaction.setAmount(result.getDouble(INDEX_TRANSACTIONS_AMOUNT));
                    transaction.setDetails(result.getString(INDEX_TRANSACTIONS_DETAILS));
                    transaction.setDate(result.getString(INDEX_TRANSACTIONS_DATE));

                    transactions.add(transaction);
                }
            }
            return transactions;

        } catch (SQLException e){
            System.out.println("Error querying report: " + e.getMessage());
            return null;
        }
    }

    public boolean saveDetails(Details details){
        return true;
    }

    public boolean editDetails(Details details){
        
    }

    public boolean changePassword(int id, String password){
        return true;
    }

    public boolean changePin(int id, String pin){
        return true;
    }





}
