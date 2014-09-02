package com.cz.easysplit.Events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;

@ParseClassName("Event")
public class Event extends ParseObject{
	public String getName(){
		return getString("name");
	}
	public void setName(String name){
		put("name", name);
	}
	public Date geteventDate(){
		return getDate("eventDate");
	}
	public void seteventDate(Date eDate){
		put("eventDate", eDate);
	}
	public ArrayList<Prepaid> getCosts() throws ParseException{
		List<Prepaid> prepaids = getList("costs");
		ArrayList<Prepaid> prepaidsFetched = new ArrayList<Prepaid>();
		for (Prepaid p : prepaids){
			Prepaid pF = (Prepaid)p.fetch();
			prepaidsFetched.add(pF);
		}
		return prepaidsFetched;
	}
	public void setCosts(ArrayList<Prepaid> costs){
		put("costs", costs);
	}
	public ArrayList<Transaction> getTransactions() throws ParseException{
		List<Transaction> transactions = getList("transactions");
		ArrayList<Transaction> transFetched = new ArrayList<Transaction>();
		for (Transaction p : transactions){
			Transaction pF = p.fetch();
			transFetched.add(pF);
		}
		return transFetched;
	}
	public void setTransactions(ArrayList<Transaction> trans){
		put("transactions", trans);
	}
	
	@Override
	public String toString(){
		return getName() + " " + geteventDate().toString();
	}
}
