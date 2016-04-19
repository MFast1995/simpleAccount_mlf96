package simpleAccount.model;

public class User {
	private String name = "";
	private int ID = 0;
	private double balance = 0.0;
	
	public User(String name, int ID, double balance)
	{
		this.name = name;
		this.ID = ID;
		this.balance = balance;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
}


