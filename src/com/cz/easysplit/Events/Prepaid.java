package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;
 
@ParseClassName("Prepaid")
public class Prepaid extends ParseObject {
	private String userId;  
	private String userName;  // Might change

	public double getAmountPaid() {
		 return getDouble("AmountPaid");
	}
	
	public void setAmountPaid(double amount){
		put("AmountPaid", amount);
	}
	
	public ParseUser getUserPointer() {
		return getParseUser("User");
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
		if (userName != null) {
			return userName + "     " + getAmountPaid();
		} else {
			return getUser().getUsername() + "     " + getAmountPaid();
		}
	}
	
	public String getUserId() {
		if (userId != null) {
			return userId;
		} else {
			return getUser().getObjectId();	
		}
	}
}
