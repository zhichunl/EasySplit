package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("Transaction")
public class Transaction extends ParseObject{
	public double getAmount(){
		return getDouble("Amount");
	}
	public void setAmount(double paid){
		put("Amount", paid);
	}
	public ParseUser getPayer() {
		try {
			return getParseUser("Payer").fetch();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setPayer(ParseUser payer){
		put("Payer", payer);
	}
	public ParseUser getPayee(){
		try {
			return getParseUser("Payee").fetch();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setPayee(ParseUser payee){
		put("Payee", payee);
	}
	public boolean getState() {
		return getBoolean("finished");
	}
	public void setState(boolean finished) {
		put("finished", finished);
	}
}
