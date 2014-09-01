package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;
 
@ParseClassName("Prepaid")
public class Prepaid extends ParseObject {

	public double getAmountPaidName() {
		 return getDouble("AmountPaid");
	}
	public void setAmountPaidName(double amount){
		put("AmountPaid", amount);
	}
	public ParseUser getUser() throws ParseException{
		return getParseUser("User").fetch();
	}
	public void setUser(ParseUser user){
		put("User", user);
	}

}
