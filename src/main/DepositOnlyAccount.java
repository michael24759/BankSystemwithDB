package main;

public class DepositOnlyAccount extends Account implements iDeposit {

	//constructor
	public DepositOnlyAccount(Integer balance, Integer cId, Integer aId) {
		super(balance, cId, aId);
		// TODO Auto-generated constructor stub
	}

	//Method for depositing money to account
	public boolean deposit(Integer amount){
		if(amount <= 0){
			System.err.println("invalid amount");
			return false;
		}
		balance += amount;
		return true;
	}
	
}
