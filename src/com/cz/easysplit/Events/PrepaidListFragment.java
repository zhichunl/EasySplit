package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;
import com.parse.ParseUser;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewGroupCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PrepaidListFragment extends ListFragment {
	public ArrayList<Prepaid> myPrepaids;
	private PrepaidAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myPrepaids = new ArrayList(); //EventLab.get(getActivity()).getEvents();
		Prepaid prepaid = new Prepaid();
		prepaid.setUser(ParseUser.getCurrentUser());
		prepaid.setAmountPaid(0);
		myPrepaids.add(prepaid);
		
		/*adapter = new ArrayAdapter<Prepaid>(getActivity(),
											android.R.layout.simple_list_item_1,
											prepaids);*/
		adapter = new PrepaidAdapter(myPrepaids);
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
	
	public ArrayList<Prepaid> getPrepaids() {
		return myPrepaids;
	}
	
	public void updatePrepaidList() {
	    //adapter.clear();
	    adapter.notifyDataSetChanged();
	}
	
	private class PrepaidAdapter extends ArrayAdapter<Prepaid> {
		
		public PrepaidAdapter(ArrayList<Prepaid> prepaids) {
			super(getActivity(), 0, prepaids);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
								.inflate(R.layout.list_item_prepaid, null);	
			}
			Prepaid p = myPrepaids.get(position);
			TextView nameView = (TextView)convertView.findViewById(R.id.list_item_prepaid_name);
			nameView.setText(p.getUser().getUsername());
			TextView amountView = (TextView)convertView.findViewById(R.id.list_item_prepaid_amount);
			amountView.setText((new Double(p.getAmountPaid())).toString());
			Button plusButton = (Button)convertView.findViewById(R.id.list_item_prepaid_add_amount);
			return convertView;
		}
		
	}
}
