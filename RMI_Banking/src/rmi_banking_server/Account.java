package rmi_banking_server;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
