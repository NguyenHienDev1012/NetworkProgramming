package ltm2008_01;

public class User {
	private String name;
	private String pass;
	private String accountNumber;
    private double money;
    
	public User(String name, String pass, String accountNumber, double money) {
		this.name = name;
		this.pass = pass;
		this.accountNumber = accountNumber;
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + ", accountNumber=" + accountNumber + ", money=" + money + "]\n";
	}
	
    
}
