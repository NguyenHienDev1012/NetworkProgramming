package tcp2017_2018_server;

public interface IBanking {
	public double getBalance(Account account);
	public double  withdraw(Account account, double amount) ;
	public double deposit(Account account, double amount) ;
	public double transfer(Account fromAccount, Account toAccount, double amount) ;
	public String report(Account account);
	public boolean checkAccount(Account account) ;

}
