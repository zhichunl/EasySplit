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
		Event event1 = new Event();
		event1.setName("Buying Food");
		event1.seteventDate(new Date(121237200));
		ArrayList<Prepaid> people = new ArrayList<Prepaid>();
		Prepaid prepaid1 = new Prepaid();
		prepaid1.setUser(ParseUser.getCurrentUser());
		prepaid1.setAmountPaidName(100);
		people.add(prepaid1);
		event1.setCosts(people);
		try {
			event1.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserEvents events = new UserEvents();
		events.setUser(ParseUser.getCurrentUser());
		ArrayList<Event> allEvents = new ArrayList<Event>();
		allEvents.add(event1);
		events.setEvents(allEvents);
		try {
			events.save();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		myEvents = new ArrayList<Event>();
		ParseQuery<UserEvents> query = ParseQuery.getQuery(UserEvents.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		try {
			ArrayList<UserEvents> uE = (ArrayList<UserEvents>)query.find();
			if (uE.size() > 0){
				UserEvents currentUserEvents = uE.get(0);
				myEvents = currentUserEvents.getEvents();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static EventLab get(Context c) {
		if (sEventLab == null) {
			sEventLab = new EventLab(c.getApplicationContext());
		}
		return sEventLab;
	}
	
	public ArrayList<Event> getEvents() {
		return myEvents;
	}
	
//	public Event getEvent(UUID id) {
//		for (Event e : myEvents) {
//			if (e.getId().equals(id)) {
//				return e;
//			}
//		}
//		return null;
//	}
//	
//	public void prependEvent(Event e) {
//		myEvents.add(0, e);   //prepend it to the list
//	}
}