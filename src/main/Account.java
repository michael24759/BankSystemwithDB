package main;

public abstract class Account {
	
    protected Integer accountId;
    protected Integer balance;
    protected Integer clientId;
    
    //Constructor
    public Account(Integer balance, Integer cId, Integer aId) {
        this.balance = balance;
        accountId = aId;
        clientId = cId;
    }
    
    //Setter & Getter
    public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
    
    
    
    
	
}