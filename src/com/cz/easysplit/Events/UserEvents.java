package com.cz.easysplit.Events;
import java.util.List;
import java.util.ArrayList;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("UserEvents")
public class UserEvents extends ParseObject {
	public ParseUser getUser() throws ParseException {
		return getParseUser("user").fetch();
	}
	public void setUser(ParseUser eventUser){
		put("user", eventUser);
	}
	public ArrayList<Event> getEvents() throws ParseException {
		List<Event> rawList = getList("Events");
		ArrayList<Event> allEvents = new ArrayList<Event>();
		for (Event e : rawList){
			Event eF = (Event)e.fetch();
			allEvents.add(eF);
		}
		return allEvents;
	}
	public void setEvents(ArrayList<Event> allEvents){
		put("Events", allEvents);
	}

}
