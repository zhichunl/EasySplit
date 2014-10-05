package com.cz.easysplit.Events;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;
 
@ParseClassName("Prepaid")
public class Prepaid extends ParseObject {
	private String userId;  
	private String userName;
	private double amountPaid = -1;
	private ParseUser currentUser;
	private ParseUser fetchedUser;

	public double getAmountPaid() {
		if (amountPaid == -1){
			amountPaid = getDouble("AmountPaid");
		}
		return amountPaid;
	}
	
	public void setAmountPaid(double amount){
		put("AmountPaid", amount);
		amountPaid = amount;
	}
	
	public ParseUser getUserPointer() {
		if (currentUser == null){
			currentUser = getParseUser("User");
		}
		return currentUser;
	}
	
	//TODO: Is returning null the best way?
	public ParseUser getUser() {
		try {
			if (fetchedUser == null){
				fetchedUser = getParseUser("User").fetch();
			}
			return fetchedUser;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setUser(ParseUser user){
		put("User", user);
		fetchedUser = user;
		userName = user.getUsername();
		currentUser = user;
	}
	
	public String getUserName() {
		if (userName == null) {
			ParseUser user = getUser();
			if (user != null) {
				userName = getUser().getUsername();
			}
		} 
		return userName;
	}

	@Override
	public String toString() {
		return getUserName() + "     " + getAmountPaid();
	}
	
	public String getUserId() {
		if (userId != null) {
			return userId;
		} else {
			return getUser().getObjectId();	
		}
	}
}
