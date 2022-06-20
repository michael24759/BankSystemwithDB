package main;

public class Client {
    
	private Integer clientID;
	private String clientName;
	private String clientAddress;
	private String clientPhone;
	
	//Constructor
    public Client(Integer ID, String name, String address, String phone) {
    	this.clientID = ID;
        this.clientName = name;
        this.clientAddress = address;
        this.clientPhone = phone;
    }
    
    //Setter Getter
    public Integer getID() {
        return clientID;
    }

    public void setID(Integer ID) {
        this.clientID = ID;
    }
    
    public String getName() {
        return clientName;
    }

    public void setName(String name) {
        this.clientName = name;
    }

    public String getAddress() {
        return clientAddress;
    }

    public void setAddress(String address) {
        this.clientAddress = address;
    }

    public String getPhone() {
        return clientPhone;
    }

    public void setPhone(String phone) {
        this.clientPhone = phone;
    }
    
    //Method Account.view();
    public void view() {
        System.out.println("Account Name : " + clientName);
        System.out.println("Account Address : " + clientAddress);
        System.out.println("Account Phone : " + clientPhone);
    }
    
}