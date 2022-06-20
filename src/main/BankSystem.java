package main;

import java.util.ArrayList;
import java.util.Scanner;

import db.Connect;
import db.SQL;

/*File Penjelasan terdapat di Note.txt*/

public class BankSystem {
	
    ArrayList<Client> Clients = new ArrayList<>();
    ArrayList<Account> Accounts = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    Connect con = Connect.getConnection();
    SQL s = new SQL();
 
    
    public void addClient() {
    	//Data Client
        System.out.print("Enter client name: ");
        String name = input.nextLine();
        System.out.print("Enter client address: ");
        String address = input.nextLine();
        System.out.print("Enter client phone: ");
        String phone = input.nextLine();
        
        //Pembuatan class Client
        Client newClient = new Client(null, name, address, phone);
        s.InsertClient(newClient);
        
        
        System.out.println("Client created successfully");
        System.out.println("Press Enter to Continue...");
        input.nextLine();
    }
    
    //Method untuk menambahkan akun
    public void addAccount(){
    	if(Clients.isEmpty()) {
    		System.out.println("There are no clients yet");
    	}
    	else {
    		printClient();
    		Integer tempID = 0;
    		boolean found = false;
    		while(!found) {
				System.out.print("Which client want to open an account[ID]: ");
				tempID = input.nextInt();
				if(SearchClID(tempID) != -1) {
					found = true;
				}
			}
    		
        	input.nextLine();
            //Memilih tipe akun
            int type = 0;
            while(type < 1 || type > 3) {
            	System.out.println("Choose preferred account type");
                System.out.println("1. Basic type");
                System.out.println("2. Strict type");
                System.out.println("3. Deposit Only type");
                System.out.print(">>> ");
                try {
					type = input.nextInt();
					input.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
				}
                
            }
            
            //memasukkan balance awal
            System.out.println("enter account balance");
            System.out.print(">>> ");
            Integer balance = input.nextInt();
            
            //Mengecek tipe bank
            if(type == 1){
                BasicAccount newAccount = new BasicAccount(balance, tempID, null);
                s.InsertBasicAccount(newAccount);
            }
            else if(type == 2) {
            	if(balance >= 1000000) {
            		StrictAccount sAccount = new StrictAccount(balance, tempID, null);
            		s.InsertStrictAccount(sAccount);
            	}
            	else {
            		System.out.println("This account require a minimum balance of Rp1000000");
    	        	while(true) {
    	        		System.out.println("enter account balance");
    	                System.out.print(">>> ");
    	                balance = input.nextInt();
    	                if(balance >= 1000000) {
    	                	break;
    	                }
    	                else {
    	                	System.out.println("Invalid amount");
    	                }
    	        	}
    	        	StrictAccount sAccount = new StrictAccount(balance, tempID, null);
    	        	s.InsertStrictAccount(sAccount);
    	        }
            }
            else if(type == 3) {
            	DepositOnlyAccount dAccount = new DepositOnlyAccount(balance, tempID, null);
            	s.InsertDepositOnlyAccount(dAccount);
            }
            
            System.out.println("Account created successfully");
            System.out.println("Press Enter to Continue...");
            input.nextLine();
            input.nextLine();
    	}
    	
    }
    
    //Method SearchAccount untuk mengambil data 1 account
    public Account search(int accountId){
        for(int i = 0; i < Accounts.size(); i++){
            if(accountId == Accounts.get(i).getAccountId()){
                return Accounts.get(i);
            }
        }
        return null;
    }
    
    //Method untuk mencari account menggunakan method search dan menampilkannya 
	public void searchForAccount(){
    	if(Accounts.isEmpty()) {
    		System.err.println("There are no Accounts");
    		System.out.println("Press Enter to Continue...");
	        input.nextLine();
    	}
    	else {
	    	System.out.print("Enter account ID: ");
	        int id = input.nextInt();
	        Account acc = search(id);
	        if(acc == null){
	            System.err.println("Account not found");
	            return;
	        }
	        System.out.println("============================================================");
	        System.out.println("Account ID: " + acc.getAccountId());
	        int index = SearchClID(acc.clientId); 
			System.out.println("Account Owner: " + Clients.get(index).getName());
			System.out.println("Account Balance: " + acc.getBalance());
			if(acc instanceof StrictAccount) {
				System.out.println("Account Type: Strict");
			}
			else if(acc instanceof BasicAccount) {
				System.out.println("Account Type: Basic");
			}
			else if(acc instanceof DepositOnlyAccount) {
				System.out.println("Account Type: Deposit Only");
			}
	        System.out.println("============================================================");
	        System.out.println("Press Enter to Continue...");
	        input.nextLine();
	        input.nextLine();
    	}
        
    }
    
    //Method untuk mencari index account
    public int SearchAccID(int accountId){
        for(int i = 0; i < Accounts.size(); i++){
            if(accountId == Accounts.get(i).getAccountId()){
                return i;
            }
        }
        return -1;
    }
    
    //Method untuk mencari index client
    public int SearchClID(int clientId){
        for(int i = 0; i < Clients.size(); i++){
            if(clientId == Clients.get(i).getID()){
                return i;
            }
        }
        return -1;
    }
    
    //method untuk menghapus client
    public void removeClient(){
    	if(Clients.isEmpty()) {
    		System.err.println("There are no Clients");
    		System.out.println("Press Enter to Continue...");
	        input.nextLine();
    	}
    	else {
	    	System.out.print("Enter client ID: ");
	        int id = input.nextInt();   
	        int index = SearchClID(id);
	        if(index==-1){
	            System.err.println("client not found");
	            return;
	        } 
	        Clients.remove(index);
	        s.DeleteClient(id);
	        System.out.println("Client successfully removed");
	        System.out.println("Press Enter to Continue...");
	        input.nextLine();
	        input.nextLine();
    	}
        
    }
    
    //Method untuk menghapus account
    public void removeAccount(){
    	if(Accounts.isEmpty()) {
    		System.err.println("There are no Accounts");
    		System.out.println("Press Enter to Continue...");
	        input.nextLine();
    	}
    	else {
	    	System.out.print("Enter account ID: ");
	        int id = input.nextInt();   
	        int index = SearchAccID(id);
	        if(index==-1){
	            System.err.println("Account not found");
	            return;
	        } 
	        Accounts.remove(index);
	        s.DeleteAccount(id);
	        System.out.println("Account successfully removed");
	        System.out.println("Press Enter to Continue...");
	        input.nextLine();
	        input.nextLine();
    	}
        
    }
    
    //Method untuk melakukan deposit pada account menggunakan ID
    public void deposit(){
    	if(Accounts.isEmpty()) {
    		System.err.println("There are no Accounts");
    		System.out.println("Press Enter to Continue...");
	        input.nextLine();
    	}
    	else {
	    	System.out.print("Enter account ID: ");
	        int id = input.nextInt();
	        int index = SearchAccID(id);
	        
	        if(index == -1){
	            System.err.println("Account not found");
	            return;
	        }
	        
	        System.out.print("Enter amount to deposit: ");
	        Integer amount = input.nextInt();
	        
	        if(Accounts.get(index) instanceof BasicAccount) {
	        	if(((BasicAccount) Accounts.get(index)).deposit(amount)) {
	        		System.out.println("Balance have been updated");
		            System.out.println("Final balance: Rp" + Accounts.get(index).getBalance());
		            s.UpdateBalance(Accounts.get(index).getBalance(), Accounts.get(index).getAccountId());
	        	} 
	        }
	        else if(Accounts.get(index) instanceof StrictAccount) {
	        	if(((StrictAccount) Accounts.get(index)).deposit(amount)) {
	        		System.out.println("Balance have been updated");
		            System.out.println("Final balance: Rp" + Accounts.get(index).getBalance());
		            s.UpdateBalance(Accounts.get(index).getBalance(), Accounts.get(index).getAccountId());
	        	}
	        }
	        else if(Accounts.get(index) instanceof DepositOnlyAccount) {
	        	if(((DepositOnlyAccount) Accounts.get(index)).deposit(amount)) {
	        		System.out.println("Balance have been updated");
		            System.out.println("Final balance: Rp" + Accounts.get(index).getBalance());
		            s.UpdateBalance(Accounts.get(index).getBalance(), Accounts.get(index).getAccountId());
	        	}
	        }

	        System.out.println("Press Enter to Continue...");
	        input.nextLine();
	        input.nextLine();
    	}
        
    }
    
    //Method untuk melakukan withdraw pada account menggunakan ID
    public void withdraw(){
    	if(Accounts.isEmpty()) {
    		System.err.println("There are no Accounts");
    		System.out.println("Press Enter to Continue...");
	        input.nextLine();
    	}
    	else {
	    	System.out.print("Enter account ID: ");
	        int id = input.nextInt();
	        int index = SearchAccID(id);
	        
	        if(index == -1){
	            System.err.println("Account not found");
	            return;
	        }   
	        
	        System.out.print("Enter amount to withdraw: ");
	        Integer amount =input.nextInt();
	        if(Accounts.get(index) instanceof BasicAccount) {
	        	if(((BasicAccount) Accounts.get(index)).withdraw(amount)) {
	        		System.out.println("Balance have been updated");
		            System.out.println("Final balance: Rp" + Accounts.get(index).getBalance());
		            s.UpdateBalance(Accounts.get(index).getBalance(), Accounts.get(index).getAccountId());
	        	} 
	        }
	        else if(Accounts.get(index) instanceof StrictAccount) {
	        	if(((StrictAccount) Accounts.get(index)).withdraw(amount)) {
	        		System.out.println("Balance have been updated");
		            System.out.println("Final balance: Rp" + Accounts.get(index).getBalance());
		            s.UpdateBalance(Accounts.get(index).getBalance(), Accounts.get(index).getAccountId());
	        	}
	        }
	        else if(Accounts.get(index) instanceof DepositOnlyAccount) {
	        	System.err.println("This Account is only for depositing!");
	        }
	        System.out.println("Press Enter to Continue...");
	        input.nextLine();
	        input.nextLine();
    	}
        
    }
    
    
    
    //Method untuk menampilkan semua Client
    public void printClient() {
    	Integer i = 1;
    	for(Client c : Clients) {
			System.out.println("CLIENT #" + i++);
			System.out.println("Client ID: " + c.getID());
			System.out.println("Client Name: " + c.getName());
			System.out.println("Client Address: " + c.getAddress());
			System.out.println("Client Phone: " + c.getPhone());
			System.out.println("======================================");
    	}
	}
    
    //Method untuk menampilkan semua Account
  	public void printAccount() {
      	if(Accounts.isEmpty()) {
      		System.out.println("There are no Accounts yet");
      		return;
      	}
      	else {
      		Integer i = 1;
      		for(Account a : Accounts) {
      			System.out.println("ACCOUNT #" + i++);
      			int index = SearchClID(a.clientId);
      			System.out.println("Account ID: " + a.getAccountId());
      			System.out.println("Account Owner: " + Clients.get(index).getName());
      			System.out.println("Account Balance: " + a.getBalance());
      			if(a instanceof StrictAccount) {
      				System.out.println("Account Type: Strict");
      			}
      			else if (a instanceof BasicAccount) {
      				System.out.println("Account Type: Basic");
      			}
      			else if (a instanceof DepositOnlyAccount) {
      				System.out.println("Account Type: Deposit Only");
      			}
      			System.out.println("======================================");
      		}
      	}
      	System.out.println("Press enter to continue...");
      	input.nextLine();
  	}
    
  	//method untuk melakukan edit pada data client
    public void editClient(){
    	printClient();
    	Integer tempID;
    	System.out.print("Input Client ID: ");
    	tempID = input.nextInt();
    	input.nextLine();
    	
    	//new data
    	System.out.print("Enter client name: ");
        String name = input.nextLine();
        System.out.print("Enter client address: ");
        String address = input.nextLine();
        System.out.print("Enter client phone: ");
        String phone = input.nextLine();
        
        s.UpdateClient(new Client(null, name, address, phone), tempID);
        
        System.out.println("Client successfully updated");
        input.nextLine();
    }
    
    //Program utama
    public BankSystem() {
        int option = 0;
        while (option != 10){
        	//Load Data from database
        	Clients.clear();
        	Accounts.clear();
        	Clients = s.loadClient();
        	Accounts = s.loadAccount();
        	
        	System.out.println();
        	System.out.println();
        	//Menu
        	System.out.println("Bank Menu");
        	System.out.println("1. Add a client");
            System.out.println("2. Add an account");
            System.out.println("3. Edit a client");
            System.out.println("4. Display all Accounts");
            System.out.println("5. Search for an account");
            System.out.println("6. Remove a client");
            System.out.println("7. Remove an account");
            System.out.println("8. Deposit balance");
            System.out.println("9. Withdraw balance");
            System.out.println("10. Exit");
            System.out.print(">>> ");
            option = input.nextInt();
            input.nextLine();
            System.out.println();
            System.out.println();
            switch (option) {
            	case 1:
            		addClient();
            		break;
            	case 2:
            		addAccount();
            		break;
            	case 3:
            		editClient();
            		break;
            	case 4:
            		printAccount();
            		break;
            	case 5:
            		searchForAccount();
            		break;
            	case 6:
            		removeClient();
            		break;
            	case 7:
            		removeAccount();
            		break;
            	case 8:
            		deposit();
            		break;
            	case 9:
            		withdraw();
            		break;
            	case 10:
            		System.out.println("Exiting...");
            		break;
            	default:
            		System.out.println("Input 1-7 only!");
            		break;
            }
        }
    }
    
    public static void main(String[] args) {
    	new BankSystem();

    }
    
}