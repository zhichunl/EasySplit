package com.cz.easysplit;

import java.util.Date;
import java.util.UUID;

public class Event {
	public String eventName;
	public Date eventDate;
	private UUID eId;
	
	public Event(String name, Date date) {
		eventName = name;
		eventDate = date;
		eId = UUID.randomUUID();
	}

	public UUID getId() {
		return eId;
	}
	@Override
	public String toString(){
		return eventName + " " + eventDate.toString();
	}
}
