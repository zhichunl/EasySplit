package com.cz.easysplit.Events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.text.format.DateFormat;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;

@ParseClassName("Event")
public class Event extends ParseObject{
	private String eventName = null;
	private Boolean confirmed = null;
	private Date eventDate = null;
	private ArrayList<Prepaid> eventPrepaids = null;
	private ArrayList<Transaction> eventTransactions = null;
	
 	public String getName(){
 		if (eventName == null) {
 			eventName = getString("name");
 		}
 		return eventName;
	}
	public void setName(String aEventName){
		eventName = aEventName;
		put("name", eventName);
	}
	
	public Boolean getConfirmed(){
		if (confirmed == null) {
			confirmed = getBoolean("confirmed");
		}
		return confirmed;
	}
	
	public void setConfirmed(Boolean b){
		confirmed = b;
		put("confirmed", confirmed);
	}
	
	public Date geteventDate(){
		if (eventDate == null) {
			eventDate = getDate("eventDate");
		}
		return eventDate;
	}
	
	public void seteventDate(Date eDate){
		put("eventDate", eDate);
	}
	
	public ArrayList<Prepaid> getCosts() {
		if (eventPrepaids != null) {
			return eventPrepaids;
		}
		List<Prepaid> prepaids = getList("costs");
		eventPrepaids = new ArrayList<Prepaid>();
		for (Prepaid p : prepaids){
			Prepaid pF;
			try {
				pF = (Prepaid)p.fetch();
				eventPrepaids.add(pF);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eventPrepaids;
	}
	
	public void setCosts(ArrayList<Prepaid> costs){
		eventPrepaids = costs;
		put("costs", costs);
	}
	
	public ArrayList<Transaction> getTransactions() {
		if (eventTransactions != null) {
			return eventTransactions;
		}
		List<Transaction> transactions = getList("transactions");
		if (transactions == null){
			return new ArrayList<Transaction>();
		}
		eventTransactions = new ArrayList<Transaction>();
		for (Transaction p : transactions){
			try {
				Transaction pF = p.fetch();
				eventTransactions.add(pF);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eventTransactions;
	}
	public void setTransactions(ArrayList<Transaction> trans){
		eventTransactions = trans;
		put("transactions", trans);
	}
	
	@Override
	public String toString(){
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return getName();
	}
}
