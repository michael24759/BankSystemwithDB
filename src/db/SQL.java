package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Account;
import main.BasicAccount;
import main.Client;
import main.DepositOnlyAccount;
import main.StrictAccount;

public class SQL {
	
	Connect con = Connect.getConnection();
	
	//load Client data from database
    public ArrayList<Client> loadClient() {
    	ArrayList<Client> temp = new ArrayList<>();
		String clientName, clientAddress, clientPhone;
		int clientID;
		
		String query = "SELECT * FROM client";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				clientID = rs.getInt("ClientID");
				clientName = rs.getString("ClientName");
				clientAddress = rs.getString("ClientAddress");
				clientPhone = rs.getString("ClientPhone");
				
				Client curr = new Client(clientID, clientName, clientAddress, clientPhone);
				temp.add(curr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
    //load BasicAccount and StrictAccount data from database
    public ArrayList<Account> loadAccount() {
    	ArrayList<Account> temp = new ArrayList<>();
		String accountType;
		int accountID, balance, clientID;
		
		String query = "SELECT * FROM account";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				clientID = rs.getInt("ClientID");
				accountID = rs.getInt("AccountID");
				balance = rs.getInt("Balance");
				accountType = rs.getString("AccountType");
				if(accountType.equalsIgnoreCase("Basic Account")) {
					Account acc = new BasicAccount(balance, clientID, accountID);
					temp.add(acc);
				}
				else if(accountType.equalsIgnoreCase("Strict Account")) {
					Account acc = new StrictAccount(balance, clientID, accountID);
					temp.add(acc);
				}
				else if(accountType.equalsIgnoreCase("DepositOnly Account")) {
					Account acc = new DepositOnlyAccount(balance, clientID, accountID);
					temp.add(acc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
    
	//inserting client data to database
	public void InsertClient(Client client) {
		String query = String.format("INSERT INTO client VALUES" 
				+ "(NULL, '%s', '%s', '%s')", 
				client.getName(),client.getAddress(), client.getPhone());
		con.executeUpdate(query);
	}
	
	//inserting BasicAccount data to database
	public void InsertBasicAccount(BasicAccount Account) {
		String query = String.format("INSERT INTO account VALUES" 
				+ "(NULL, %d, %d, '%s')", 
				Account.getClientId(), Account.getBalance(), "Basic Account");
		con.executeUpdate(query);
	}
	
	//inserting StrictAccount data to database
	public void InsertStrictAccount(StrictAccount Account) {
		String query = String.format("INSERT INTO account(`AccountID`, `ClientID`, `Balance`, `AccountType`) VALUES" 
				+ "(NULL, %d, %d, '%s')", 
				Account.getClientId(), Account.getBalance(), "Strict Account");
		con.executeUpdate(query);
	}
	
	//inserting DepositOnlyAccount data to database
	public void InsertDepositOnlyAccount(DepositOnlyAccount Account) {
		String query = String.format("INSERT INTO account(`AccountID`, `ClientID`, `Balance`, `AccountType`) VALUES" 
				+ "(NULL, %d, %d, '%s')", 
				Account.getClientId(), Account.getBalance(), "DepositOnly Account");
		con.executeUpdate(query);
	}
	
	//updating client data in database
	public void UpdateClient(Client client, int id) {
		String query = String.format("UPDATE client SET "
				+ "ClientName = '%s',ClientAddress = '%s',ClientPhone = '%s' WHERE " 
				+ "ClientID = '%d'", client.getName(), client.getAddress(),client.getPhone(), id);
		con.executeUpdate(query);
	}
	
	//deleting client data from database using ClientID
	public void DeleteClient(Integer id) {
		String query = "DELETE FROM client WHERE ClientID = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//deleting account data from database using AccountID
	public void DeleteAccount(Integer id) {
		String query = "DELETE FROM account WHERE AccountID= ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//updating account's balance in database
	public void UpdateBalance(Integer amount, Integer id) {
		String query = String.format("UPDATE account SET "
				+ "Balance = %d WHERE "
				+ "AccountID = %d", amount, id);
		con.executeUpdate(query);
	}
	
}
