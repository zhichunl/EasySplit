package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;
import com.cz.easysplit.R.menu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventListFragment extends ListFragment {
	private ArrayList<Event> myEvents;
	private ArrayAdapter<Event> adapter;
	
	public void setMyEvents(ArrayList<Event> myEvents) {
		this.myEvents = myEvents;
	}

	public ArrayList<Event> getMyEvents() {
		return myEvents;
	}

	public ArrayAdapter<Event> getAdapter() {
		return adapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("my Events");
		myEvents = EventLab.get(getActivity()).getEvents();
		
		adapter = new ArrayAdapter<Event>(getActivity(),
											android.R.layout.simple_list_item_1,
											myEvents);
		setListAdapter(adapter);
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		    }
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.event_list_action_bar, menu);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		myEvents = EventLab.get(getActivity()).getEvents();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		
	    EventEditingFragment fragment = (EventEditingFragment)fm.findFragmentById(R.id.activity_event_editing);
	    
	    if (fragment == null) {
	    	fragment = new EventEditingFragment();
	    }
	    getActivity().setTitle("Event");
	    transaction.replace(R.id.fragmentContainer, fragment);
	    transaction.addToBackStack(null);
	    transaction.commit();
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	if (NavUtils.getParentActivityName(getActivity()) != null) {
	                NavUtils.navigateUpFromSameTask(getActivity());
	            }	        	
	        	return true;
	        case R.id.addEvent:	    		
	    		FragmentManager fm = getActivity().getSupportFragmentManager();
	    		FragmentTransaction transaction = fm.beginTransaction();
	    		
	    	    EventEditingFragment fragment = (EventEditingFragment)fm.findFragmentById(R.id.activity_event_editing);
	    	    
	    	    if (fragment == null) {
	    	    	fragment = new EventEditingFragment();
	    	    }
	    	    getActivity().setTitle("New Event");
	    	    transaction.replace(R.id.fragmentContainer, fragment);
	    	    transaction.addToBackStack(null);
	    	    transaction.commit();
	    	    return true;
	        default:
	        	return super.onOptionsItemSelected(item);

	    } 
	}
	
	/*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    doWhateverAfterScreenViewIsRendered();
    }*/
}
