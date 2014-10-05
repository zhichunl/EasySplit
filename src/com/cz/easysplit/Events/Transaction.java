package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("Transaction")
public class Transaction extends ParseObject{
	private double amount = -1;
	private ParseUser payer;
	private ParseUser payee;
	private Boolean finished = null;
	public double getAmount(){
		if (amount == -1){
			amount = getDouble("Amount");
		}
		return amount;
	}
	public void setAmount(double paid){
		put("Amount", paid);
		amount = paid;
	}
	public ParseUser getPayer() {
		try {
			if (payer == null){
				payer = getParseUser("Payer").fetch();
			}
			return payer;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setPayer(ParseUser payer){
		put("Payer", payer);
		this.payer = payer;
	}
	public ParseUser getPayee(){
		try {
			if (payee == null){
				payee = getParseUser("Payee").fetch();
			}
			return payee;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setPayee(ParseUser payee){
		put("Payee", payee);
		this.payee = payee;
	}
	public boolean getFinished() {
		if (finished == null){
			finished = getBoolean("finished");
		}
		return finished;
	}
	public void setFinished(boolean finished) {
		put("finished", finished);
		this.finished = finished;
	}
}
