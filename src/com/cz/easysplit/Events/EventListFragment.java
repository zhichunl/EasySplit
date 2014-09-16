package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;
import com.cz.easysplit.R.menu;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
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
		this.myEvents = EventLab.get(getActivity()).getEvents();
		
		adapter = new ArrayAdapter<Event>(getActivity(),
											android.R.layout.simple_list_item_1,
											this.myEvents);
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
		this.myEvents = EventLab.get(getActivity()).getEvents();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final int p = position;
		builder.setMessage("What would you like to do?");
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", 
    		new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Event eventToDelete = getMyEvents().get(p);
					try {
						eventToDelete.delete();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ArrayList<Event> events = getMyEvents();
					events.remove(p);
					setMyEvents(events);
					ParseQuery<UserEvents> query = ParseQuery.getQuery(UserEvents.class);
            		query.whereEqualTo("user", ParseUser.getCurrentUser());
            		UserEvents uE;
					try {
						uE = query.getFirst();
						uE.setEvents(myEvents);
	            		uE.save();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
					}
					adapter = new ArrayAdapter<Event>(getActivity(),
							android.R.layout.simple_list_item_1,
							getMyEvents());
					setListAdapter(adapter);
				}
			});
    		builder.setNegativeButton("View",
            new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			FragmentManager fm = getActivity().getSupportFragmentManager();
    			FragmentTransaction transaction = fm.beginTransaction();
    			
    		    EventEditingFragment fragment = (EventEditingFragment)fm.findFragmentById(R.id.activity_event_editing);
    		    
    		    if (fragment == null) {
    		    	fragment = new EventEditingFragment();
    		    }
    		    getActivity().setTitle("Event");
    		    Event curEvent = myEvents.get(p);
    		    fragment.curEvent = curEvent;
    		    transaction.replace(R.id.fragmentContainer, fragment);
    		    //transaction.detach(fm.findFragmentById(R.id.fragmentContainer));
    		    //transaction.attach(fragment);
    		    transaction.addToBackStack(null);
    		    transaction.commit();
    		}		            
        });
        AlertDialog alert = builder.create();
        alert.show();
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
