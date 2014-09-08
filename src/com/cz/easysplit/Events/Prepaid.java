package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;
 
@ParseClassName("Prepaid")
public class Prepaid extends ParseObject {

	public double getAmountPaid() {
		 return getDouble("AmountPaid");
	}
	
	public void setAmountPaid(double amount){
		put("AmountPaid", amount);
	}
	
	//TODO: Is returning null the best way?
	public ParseUser getUser() {
		try {
			return getParseUser("User").fetch();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setUser(ParseUser user){
		put("User", user);
	}

	@Override
	public String toString() {
		return getUser().getUsername() + "     " + getAmountPaid();
	}
}
