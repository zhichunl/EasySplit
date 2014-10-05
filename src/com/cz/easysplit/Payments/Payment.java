package com.cz.easysplit.Payments;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Payment")
public class Payment extends ParseObject {
	private Double amount = null;
	private ParseUser fromUser = null;
	private ParseUser toUser = null; 
	
	public double getAmount() {
		if (amount == null) {
			amount = getDouble("amount");
		}
		return amount;
	}
	
	public void setAmount(double a) {
		amount = a;
		put("amount", amount);
		
	}
	
	public ParseUser getFrom() throws ParseException {
		if (fromUser == null) {
			fromUser= getParseUser("from").fetch();
		} 
		return fromUser;
	}
	
	public void setFrom(ParseUser user) {
		fromUser = user;
		put("from", user);
	}
	
	public ParseUser getTo() throws ParseException {
		if (toUser == null) {
			toUser = getParseUser("to").fetch();
		}
		return toUser;
	}
	
	public void setTo(ParseUser user) {
		toUser = user;
		put("to", user);
	}

	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			return getTo().getUsername() + "       " + df.format(getAmount());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
