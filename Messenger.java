package wallet;

import project.Account;
import project.Transaction;
import project.User;
import wallet.data.Datasource;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Messenger extends Thread {

    private Socket socket;

    public Messenger(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String confirmation = "ok";
            String error = "error";

            Object[] request = (Object[]) input.readObject();
            System.out.println("Received request: " + request[0]);

            //=Handle requests=//

            switch ((String) request[0]){
                case "login":
                    try{
                        String username = (String) request[1];
                        String password = (String) request[2];
                        User user = Datasource.getInstance().login(username, password);
                        output.writeObject(user);
                    } catch (IOException e){
                        System.out.println("Error sending logged in user to client: " + e.getMessage());
                    } break;
                case "createUser":
                    try{
                        User user = (User) request[1];
                        if(Datasource.getInstance().createUser(user)){
                            output.writeObject(confirmation);
                        } else {
                            output.writeObject(error);
                        }
                    } catch (IOException e){
                        System.out.println("Error sending confirmation for created user: " + e.getMessage());
                    } break;
                case "queryAccountsForTable":
                    try{
                        int id = (Integer) request[1];
                        List<Account> accounts = Datasource.getInstance().queryAccountsForTable(id);
                        output.writeObject(accounts);
                    } catch (IOException e){
                        System.out.println("Error sending accounts to client: " + e.getMessage());
                    } break;
                case "queryTransactionForTable":
                    try{
                        String account = (String) request[1];
                        List<Transaction> transactions = Datasource.getInstance().queryTransactionsForTable(account);
                        output.writeObject(transactions);
                    } catch (IOException e){
                        System.out.println("Error sending transactions to client: " + e.getMessage());
                    } break;
                case "queryTransaction":
                    try{
                        int id = (Integer) request[1];
                        Transaction transaction = Datasource.getInstance().queryTransaction(id);
                        output.writeObject(transaction);
                    } catch (IOException e){
                        System.out.println("Erro sending transaction info to client: " + e.getMessage());
                    } break;
                case "saveTransaction":
                    try{
                        Transaction transaction = (Transaction) request[1];
                        if(Datasource.getInstance().saveTransaction(transaction)){
                            output.writeObject(confirmation);
                        } else {
                            output.writeObject(error);
                        }
                    } catch (IOException e){
                        System.out.println("Error sending transaction confirmation to client");
                    } break;
                case "editTransaction":
                    try {
                        Transaction transaction = (Transaction) request[1];
                        if(Datasource.getInstance().editTransaction(transaction)){
                            output.writeObject(confirmation);
                        } else {
                            output.writeObject(error);
                        }
                    } catch(IOException e){
                        System.out.println("Error sending confirmation for edited transaction: " + e.getMessage());
                    } break;
                case "queryStatement":
                    try{
                        String month = (String) request[1];
                        String year = (String) request[2];
                        String account = (String) request[3];
                        List<Transaction> transactions = Datasource.getInstance().queryStatement(month, year, account);
                        output.writeObject(transactions);
                    } catch (IOException e){
                        System.out.println("Error sending statement to client: " + e.getMessage());
                    }
                default:
                    System.out.println("REQUEST NOT VALID");
                    break;
            }

        } catch (IOException e){
            System.out.println("IOException found: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Class ot found: " + e.getMessage());
        }
    }

}
