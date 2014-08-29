package com.cz.easysplit;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

public class Payment {
	public String from;
	public String to;
	private UUID pid;
	public double amount;
	public boolean paid;
	
	public Payment(String curFrom, String curTo, double curAmount) {
		from = curFrom;
		to = curTo;
		amount = curAmount;
		pid = UUID.randomUUID();
		paid = false;
	}

	public UUID getId() {
		return pid;
	}
	
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.00"); 
		return to + "             " + df.format(amount);
	}
}
