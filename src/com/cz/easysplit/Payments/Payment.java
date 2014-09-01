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
	/*public String from;
	public String to;
	public double amount;
	public boolean paid;*/
	
	public double getAmount() {
		return getDouble("amount");
	}
	
	public void setAmount(double amount) {
		put("amount", amount);
	}
	
	public ParseUser getFrom() throws ParseException {
		return getParseUser("from").fetch();
	}
	
	public void setFrom(ParseUser user) {
		put("from", user);
	}
	
	public ParseUser getTo() throws ParseException {
		return getParseUser("to").fetch();
	}
	
	public void setTo(ParseUser user) {
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
