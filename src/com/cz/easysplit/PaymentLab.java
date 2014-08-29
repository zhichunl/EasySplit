package com.cz.easysplit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class PaymentLab {
	private static PaymentLab sPaymentLab;
	private Context myAppContext;
	private ArrayList<Payment> myPayments;
	
	private PaymentLab(Context appContext) {
		myAppContext = appContext;
		Payment p1 = new Payment("Coco", "Zhichun", -100.00);
		Payment p2 = new Payment("Coco", "Tian", 200);
		myPayments = new ArrayList<Payment>();
		myPayments.add(0, p1);
		myPayments.add(0, p2);
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
	
	public Payment getPayment(UUID id) {
		for (Payment e : myPayments) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}
	
	public void prependPayment(Payment e) {
		myPayments.add(0, e);   //prepend it to the list
	}
}