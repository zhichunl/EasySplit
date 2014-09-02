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
	public ParseUser getPayer() throws ParseException {
		return getParseUser("Payer").fetch();
	}
	public void setPayer(ParseUser payer){
		put("Payer", payer);
	}
	public ParseUser getPayee() throws ParseException{
		return getParseUser("Payee").fetch();
	}
	public void setPayee(ParseUser payee){
		put("Payee", payee);
	}
}