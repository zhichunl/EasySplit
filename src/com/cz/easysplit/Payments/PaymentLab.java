package com.cz.easysplit.Payments;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.content.Context;

public class PaymentLab {
	private static PaymentLab sPaymentLab;
	private Context myAppContext;
	private ArrayList<Payment> myPayments;
	
	private PaymentLab(Context appContext) {
		myAppContext = appContext;
		/*Payment p1 = new Payment();
		p1.setFrom(ParseUser.getCurrentUser());
		
		ParseQuery<ParseUser> query1 = ParseUser.getQuery();
		query1.whereEqualTo("username", "coconut");
		try {
			ParseUser user2 = (ParseUser) query1.get("HxC9rrsq8r");
			p1.setTo(user2);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				
		p1.setAmount(52.0);
		try {
			p1.save();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		//Payment p2 = new Payment("Coco", "Tian", 200);
		myPayments = new ArrayList<Payment>();
		ParseQuery<Payment> query = ParseQuery.getQuery(Payment.class);
		query.whereEqualTo("from", ParseUser.getCurrentUser());
		try {
			myPayments = (ArrayList<Payment>)query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//myPayments.add(0, p1);
		//myPayments.add(0, p2);
	}
	
	public static PaymentLab get(Context c) {
		if (sPaymentLab == null) {
			sPaymentLab = new PaymentLab(c.getApplicationContext());
		}
		return sPaymentLab;
	}
	
	public ArrayList<Payment> getPayments() {
		return myPayments;
	}
	
	/*public Payment getPayment(String) {
		for (Payment e : myPayments) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}*/
	
	/*public void prependPayment(Payment e) {
		myPayments.add(0, e);   //prepend it to the list
	}*/
}