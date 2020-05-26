package rmi_banking_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BankingImpl extends UnicastRemoteObject implements IBanking {
	private static final long serialVersionUID = 1L;
	private Data data=new Data();
    private ArrayList<Account> listAccounts;
    private Diary diary;
	protected BankingImpl() throws RemoteException {
		super();
		listAccounts=data.createData();
	}

	@Override
	public double getBalance(Account account) throws RemoteException {
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
	public double withdraw(Account account, double amount) throws RemoteException {
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
	public double deposit(Account account, double amount) throws RemoteException {
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

	@Override
	public double transfer(Account fromAccount, Account toAccount, double amount) throws RemoteException {
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
			throw new RemoteException("So tai khoan khong ton tai!!!");
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
	public String report(Account account) throws RemoteException {
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
	public boolean checkAccount(Account account) throws RemoteException {
		for (int i = 0; i < listAccounts.size(); i++) {
			if(listAccounts.get(i).getAccountNumber().equals(account.getAccountNumber())&&listAccounts.get(i).getPinCode().equals(account.getPinCode())){
				return true;
			}
		}
		return false;
	}

}
