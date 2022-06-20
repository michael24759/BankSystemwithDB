package main;
public class StrictAccount extends Account implements iDeposit, iWithdraw {
    private Integer MinimumBalance;
    
    //Constructor
    public StrictAccount(Integer balance, Integer cID, Integer aID){
        super(balance, cID, aID);
        MinimumBalance = 1000000;
    }
    
    //Setter & Getter
    public Integer getMinimumBalance() {
        return MinimumBalance;
    }

    public void setMinimumBalance(Integer MinimumBalance) {
        this.MinimumBalance = MinimumBalance;
    }
    
    
    //Method for withdrawing money to account
    public boolean withdraw(Integer Amount) {
        if(balance - Amount >= MinimumBalance){
            balance -= Amount;
            return true;
        }
        System.err.println("Invalid amount");
        return false;
    }
    
    //Method for depositing money to account
    public boolean deposit(Integer Amount) {
        if(Amount < 100000){
            System.err.println("Invalid amount | Minimum amount: Rp100.000");
            return false;
        }
        balance += Amount;
        return true;
    }
    
}