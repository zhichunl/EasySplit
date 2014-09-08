package com.cz.easysplit.Events;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.cz.easysplit.R;
import com.cz.easysplit.General.MainActivity;
import com.cz.easysplit.General.SignUpActivity;
import com.cz.easysplit.R.menu;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class EventEditingFragment extends Fragment {
	private Button addAPersonButton;
	private Button saveButton;
	private View inflatedView;
	private PrepaidListFragment prepaidListFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("New Event");
		
		/*ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(getActivity(),
											android.R.layout.simple_list_item_1,
											new ArrayList());
		setListAdapter(adapter);
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		 }*/
		    
		setHasOptionsMenu(true);
		prepaidListFragment = new PrepaidListFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.eventAttenderContainer, prepaidListFragment).commit();
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflatedView = inflater.inflate(R.layout.activity_event_editing, container, false);
		addAPersonButton = (Button) inflatedView.findViewById(R.id.addAPerson);
	    
	    addAPersonButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		final EditText usernameText = (EditText) inflatedView.findViewById(R.id.person_to_add_to_event);
        		String username = usernameText.getText().toString();
    			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        		// check if the username already exists. TODO: Too slow??
        		for (Prepaid p : prepaidListFragment.getPrepaids()) {     		
        			if (p.getUser().getUsername().equals(username)) {
					    builder.setMessage("This user has already been added.");
					    builder.setCancelable(true);
					    builder.setNegativeButton("Okay",
					            new DialogInterface.OnClickListener() {
					    		public void onClick(DialogInterface dialog, int id) {
					            dialog.cancel();
					        }		            
					    });

					    AlertDialog alert = builder.create();
					    alert.show();
						usernameText.setText("");
						return;
					}
        		}
        		
        		ParseQuery<ParseUser> query = ParseUser.getQuery();
        		query.whereEqualTo("username", username);
				try {
					ArrayList<ParseUser> users = (ArrayList<ParseUser>) query.find();
					if (users.size() > 0) {
	            		Prepaid prepaid = new Prepaid();
	        			prepaid.setUser(users.get(0));
	            		prepaid.setAmountPaid(0);
	            		prepaidListFragment.getPrepaids().add(prepaid);
	            		prepaidListFragment.updatePrepaidList();
	        		} else {
			            builder.setMessage("This user does not exist.");
			            builder.setCancelable(true);
			            builder.setNegativeButton("Okay",
			                    new DialogInterface.OnClickListener() {
			            		public void onClick(DialogInterface dialog, int id) {
			                    dialog.cancel();
			                }		            
			            });

			            AlertDialog alert = builder.create();
			            alert.show();
	        		}
            		usernameText.setText("");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		      		
        	}
        });
	    
		return inflatedView;
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.event_editting_action_bar, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	if (NavUtils.getParentActivityName(getActivity()) != null) {
	                NavUtils.navigateUpFromSameTask(getActivity());
	            }	        	
	        	return true;
	        // TODO: Does not save seems that
	        case R.id.event_save: {
            		Event newEvent = new Event();
            		ArrayList<Prepaid> prepaids = prepaidListFragment.getPrepaids();
            		for (Prepaid p : prepaids) {
        				try {
    						p.save();
    					} catch (ParseException e) {
    						e.printStackTrace();
    					}
            		}
            		newEvent.setName("default event name");
            		newEvent.seteventDate(new Date(911237200));
            		newEvent.setCosts(prepaids);
            		try {
            			newEvent.save();
            		} catch (ParseException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		
            		
            		ParseQuery<UserEvents> query = ParseQuery.getQuery(UserEvents.class);
            		query.whereEqualTo("user", ParseUser.getCurrentUser());
            		try {
            			UserEvents uE = (UserEvents)query.getFirst();
            			if (uE == null) {
            				UserEvents newUE = new UserEvents();
            				newUE.setUser(ParseUser.getCurrentUser());
            				ArrayList<Event> allEvents = new ArrayList<Event>();
            				allEvents.add(newEvent);
            				newUE.setEvents(allEvents);
            				newUE.save();
            			} else {       				
            				ArrayList<Event> events = uE.getEvents();
            				events.add(newEvent);
            				uE.setEvents(events);
            				uE.save();
            			}		
            		} catch (ParseException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
    	        	if (NavUtils.getParentActivityName(getActivity()) != null) {
    	                NavUtils.navigateUpFromSameTask(getActivity());
    	            }	  
	        }
        	return true;
	        default:
	        	return super.onOptionsItemSelected(item);
	    } 
	}

}
