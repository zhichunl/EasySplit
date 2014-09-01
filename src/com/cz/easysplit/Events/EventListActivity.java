package com.cz.easysplit.Events;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.cz.easysplit.R;
import com.cz.easysplit.R.id;
import com.cz.easysplit.R.layout;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class EventListActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		FragmentManager fm = getSupportFragmentManager();
	    EventListFragment fragment = (EventListFragment)fm.findFragmentById(R.id.fragmentContainer);
	    if (fragment == null) {
	    	fragment = new EventListFragment();
	        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit(); 
	    }
	}
}
