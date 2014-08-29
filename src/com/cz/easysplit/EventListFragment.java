package com.cz.easysplit;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class EventListFragment extends ListFragment {
	private ArrayList<Event> myEvents;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("my Events");
		myEvents = EventLab.get(getActivity()).getEvents();
		
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(),
											android.R.layout.simple_list_item_1,
											myEvents);
		setListAdapter(adapter);
	}
}
