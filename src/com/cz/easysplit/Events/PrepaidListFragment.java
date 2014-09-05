package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.parse.ParseUser;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class PrepaidListFragment extends ListFragment {
	public ArrayList<Prepaid> prepaids;
	private ArrayAdapter<Prepaid> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prepaids = new ArrayList(); //EventLab.get(getActivity()).getEvents();
		Prepaid prepaid = new Prepaid();
		prepaid.setUser(ParseUser.getCurrentUser());
		prepaid.setAmountPaid(0);
		prepaids.add(prepaid);
		
		adapter = new ArrayAdapter<Prepaid>(getActivity(),
											android.R.layout.simple_list_item_1,
											prepaids);
		setListAdapter(adapter);
		/*Event event1 = new Event();
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
		//allEvents.add(event1);
		events.setEvents(allEvents);
		try {
			events.save();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		 /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		    }
		setHasOptionsMenu(true);*/
	}
	
	public void updatePrepaidList() {
	    //adapter.clear();
	    adapter.notifyDataSetChanged();
	}
}
