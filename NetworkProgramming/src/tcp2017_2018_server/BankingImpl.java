package tcp2017_2018_server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BankingImpl implements IBanking{
	private Data data=new Data();
    private ArrayList<Account> listAccounts;
    private Diary diary;
	protected BankingImpl() {
		super();
		listAccounts=data.createData();
	}

	@Override
	public double getBalance(Account account) {
		double balance=0.0;
		for (int i = 0; i < listAccounts.size(); i++) {
			Account acc=listAccounts.get(i);
			if(acc.getAccountNumber().equals(account.getAccountNumber())&& acc.getPinCode().equals(account.getPinCode())){
				balance=acc.getAmount();
				acc.getListDiarys().add(new Diary(getDate(), "Xem", acc.getAmount(), acc.getAmount()));
				return balance;
			}
		}
		
		return 0;
	}

	@Override
	public double withdraw(Account account, double amount)  {
		Account acc=null;
		for (int i = 0; i < listAccounts.size(); i++) {
			acc=listAccounts.get(i);
			if(acc.getAccountNumber().equals(account.getAccountNumber())&& acc.getPinCode().equals(account.getPinCode())){
				acc.setAmount(acc.getAmount()-amount);
				acc.getListDiarys().add(new Diary(getDate(), "RUT", amount, amount));
				return acc.getAmount();
			}
		}
		return 0;
	}

	@Override
	public double deposit(Account account, double amount) {
		Account acc=null;
		for (int i = 0; i < listAccounts.size(); i++) {
			 acc=listAccounts.get(i);
			if(acc.getAccountNumber().equals(account.getAccountNumber())&& acc.getPinCode().equals(account.getPinCode())){
				acc.setAmount(acc.getAmount()+amount);
				acc.getListDiarys().add(new Diary(getDate(), "GUI", amount, amount));
				return acc.getAmount();
			}
		}
		return 0;
	}

	public double transfer(Account fromAccount, Account toAccount, double amount) {
		double rest=0.0;
		int tmp=0;
		Account acc = null;
		for (int i = 0; i < listAccounts.size(); i++) {
			 acc=listAccounts.get(i);
			if(acc.getAccountNumber().equals(toAccount.getAccountNumber())){
				tmp++;
		    }
		}
		if(tmp==0){
		//	throw new RemoteException("So tai khoan khong ton tai!!!");
		}
		else{
		for (int i = 0; i < listAccounts.size(); i++) {
			 acc=listAccounts.get(i);
			if(acc.getAccountNumber().equals(fromAccount.getAccountNumber())&& acc.getPinCode().equals(fromAccount.getPinCode())){
				acc.setAmount(acc.getAmount()-amount);
				acc.getListDiarys().add(new Diary(getDate(), "CHUYEN TIEN", amount, amount));
				rest=acc.getAmount();
			}
			
				if(acc.getAccountNumber().equals(toAccount.getAccountNumber())){
				acc.setAmount(acc.getAmount()+amount);
			}
		   }
		}
		return rest;
	}

	@Override
	public String report(Account account)  {
		String diary="";
		for (int i = 0; i <listAccounts.size(); i++) {
			if(listAccounts.get(i).getAccountNumber().equals(account.getAccountNumber())&&listAccounts.get(i).getPinCode().equals(account.getPinCode())){
				for (int j = 0; j< listAccounts.get(i).getListDiarys().size(); j++) {
					diary+=listAccounts.get(i).getListDiarys().get(j)+"\n";
				}
			}
		}

		return diary;
		
		
	}
    public Date getDate(){
    	Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		return date;
    	
    }

	@Override
	public boolean checkAccount(Account account){
		for (int i = 0; i < listAccounts.size(); i++) {
			if(listAccounts.get(i).getAccountNumber().equals(account.getAccountNumber())&&listAccounts.get(i).getPinCode().equals(account.getPinCode())){
				return true;
			}
		}
		return false;
	}

}
