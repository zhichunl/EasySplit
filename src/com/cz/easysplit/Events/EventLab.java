package com.cz.easysplit.Events;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import com.cz.easysplit.Payments.Payment;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import android.content.Context;

public class EventLab {
	private static EventLab sEventLab;
	private Context myAppContext;
	private ArrayList<Event> myEvents;
	
	private EventLab(Context appContext) {
		myAppContext = appContext;
		ParseQuery<UserEvents> query = ParseQuery.getQuery(UserEvents.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		myEvents = new ArrayList<Event>();
		try {
			//ArrayList<UserEvents> uE = (ArrayList<UserEvents>)query.find();
			UserEvents uE = (UserEvents)query.getFirst();
			myEvents = uE.getEvents();			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public static EventLab get(Context c) {
		if (sEventLab == null) {
			sEventLab = new EventLab(c.getApplicationContext());
		}
		return sEventLab;
	}
	
	public ArrayList<Event> getEvents() {
		ParseQuery<UserEvents> query = ParseQuery.getQuery(UserEvents.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		myEvents = new ArrayList<Event>();
		try {
			//ArrayList<UserEvents> uE = (ArrayList<UserEvents>)query.find();
			UserEvents uE = (UserEvents)query.getFirst();
			myEvents = uE.getEvents();			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return myEvents;
	}
}