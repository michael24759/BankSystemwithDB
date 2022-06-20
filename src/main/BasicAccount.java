package main;

public class BasicAccount extends Account implements iDeposit,iWithdraw {

	//constructor
	public BasicAccount(Integer balance, Integer cId, Integer aId) {
		super(balance, cId, aId);
		// TODO Auto-generated constructor stub
	}
	
	//Method for withdrawing money to account
    public boolean withdraw(Integer amount){
    
        if(amount < 0){
            System.err.println("Invalid amount");
            return false;
        }
        if(amount > balance){
            System.err.println("Insufficient balance");
            return false;
        }
        balance -= amount;
        return true;
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
