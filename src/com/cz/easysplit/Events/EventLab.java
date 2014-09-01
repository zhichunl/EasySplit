package com.cz.easysplit.Events;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;


import android.content.Context;

public class EventLab {
	private static EventLab sEventLab;
	private Context myAppContext;
	private ArrayList<Event> myEvents;
	
	private EventLab(Context appContext) {
		myAppContext = appContext;
		Event e1 = new Event("Eating with Zhichun hfjdksahih ghjk duytf gyu drdu yuftf iyti", new Date(1220227200));
		Event e2 = new Event("Doing hw with Terence", new Date(120237200));
		myEvents = new ArrayList<Event>();
		myEvents.add(0, e1);
		myEvents.add(0, e2);
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
	
	public Event getEvent(UUID id) {
		for (Event e : myEvents) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}
	
	public void prependEvent(Event e) {
		myEvents.add(0, e);   //prepend it to the list
	}
}