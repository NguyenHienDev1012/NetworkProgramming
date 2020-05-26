package rmi_banking_server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String action;
	private double amount;
	private double total;
	private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
	
	public Diary(Date date, String action, double amount, double total) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Diary [date=" + String.valueOf(sdf.format(date)) + ", action=" + action + ", amount=" + amount + ", total=" + total + "]";
	}
}
