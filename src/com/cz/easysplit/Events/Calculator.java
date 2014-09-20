package com.cz.easysplit.Events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.parse.ParseUser;

class Info {
	public ParseUser user;
	public double amount;
	
	public Info(ParseUser myUser, double myAmount) {
		user = myUser;
		amount = myAmount;
	}
	
	public boolean canIgnore() {
		return amount < 0.01;
	}
}

public class Calculator {
	private static double truncate(double d) {
	    return Math.round(d * 100) / 100.0;
	}
	
	public static ArrayList<Transaction> splitEvenly(ArrayList<Prepaid> costs) {
		double sum = 0;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		for (Prepaid p : costs) {
			sum += p.getAmountPaid();
		}
		double ave = sum / costs.size();
		
		LinkedList<Info> payees = new LinkedList<Info>();
		LinkedList<Info> payers = new LinkedList<Info>();
		
		for (Prepaid p: costs) {
			double amount = p.getAmountPaid() - ave;
			if (amount > 0) {
				payees.add(new Info(p.getUserPointer(), amount));
			} else if (amount < 0) {
				payers.add(new Info(p.getUserPointer(), -amount));
			}			
		}
		
		// Sort payees in descending way
	    Collections.sort(payees, new Comparator<Info>() {
	         @Override
	         public int compare(Info o1, Info o2) {
	             return -Double.compare(o1.amount, o2.amount);
	         }
	     });
	   
		// Sort payees in aescending way
	    Collections.sort(payers, new Comparator<Info>() {
	         @Override
	         public int compare(Info o1, Info o2) {
	             return Double.compare(o1.amount, o2.amount);
	         }
	     });

		while (!payees.isEmpty() && !payers.isEmpty()) {
			Info payerInfo = payers.getFirst();
			Info payeeInfo = payees.getFirst();
			double amount = truncate(Math.min(payerInfo.amount, payeeInfo.amount));
			Transaction trans = new Transaction();
			trans.setAmount(amount);
			trans.setPayee(payeeInfo.user);
			trans.setPayer(payerInfo.user);
			trans.setFinished(false);
			payers.getFirst().amount -= amount;
			payees.getFirst().amount -= amount;
			
			if (payers.getFirst().canIgnore()) {
				payers.removeFirst();
			}
			if (payees.getFirst().canIgnore()) {
				payees.removeFirst();
			}
						
			transactions.add(trans);
			
		}
	   
		return transactions;
	}
}
