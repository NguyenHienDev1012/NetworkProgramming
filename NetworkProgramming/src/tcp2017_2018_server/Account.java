package tcp2017_2018_server;

import java.util.ArrayList;
public class Account{
	private String accountNumber;
	private String pinCode;
	private double amount;
	private ArrayList<Diary> listDiarys;
	public Account(String accountNumber, String pinCode,double amount, ArrayList<Diary> listDiarys) {
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
		this.amount=amount;
		this.listDiarys = listDiarys;
	}


	public Account(String accountNumber, String pinCode) {
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
	}
	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	public Account(String accountNumber, String pinCode, double amount) {
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
		this.amount=amount;
	}

	

	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public ArrayList<Diary> getListDiarys() {
		return listDiarys;
	}


	public void setListDiarys(ArrayList<Diary> listDiarys) {
		this.listDiarys = listDiarys;
	}
}
