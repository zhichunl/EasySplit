package com.cz.easysplit;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class PaymentListActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		FragmentManager fm = getSupportFragmentManager();
	    PaymentListFragment fragment = (PaymentListFragment)fm.findFragmentById(R.id.fragmentContainer);
	    if (fragment == null) {
	    	fragment = new PaymentListFragment();
	        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit(); 
	    }
	}
}
