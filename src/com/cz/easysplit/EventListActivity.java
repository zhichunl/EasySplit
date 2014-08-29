package com.cz.easysplit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class EventListActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		
		FragmentManager fm = getSupportFragmentManager();
	    EventListFragment fragment = (EventListFragment)fm.findFragmentById(R.id.fragmentContainer);
	    if (fragment == null) {
	    	fragment = new EventListFragment();
	        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit(); 
	    }
	}
	
}
