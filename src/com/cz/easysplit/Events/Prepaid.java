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
	public ParseUser getUser() throws ParseException{
		return getParseUser("User").fetch();
	}
	public void setUser(ParseUser user){
		put("User", user);
	}

	@Override
	public String toString() {
		try {
			return getUser().getUsername() + "     " + getAmountPaid();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
