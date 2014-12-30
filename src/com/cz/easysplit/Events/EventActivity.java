package com.cz.easysplit.Events;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.cz.easysplit.R;
import com.cz.easysplit.R.id;
import com.cz.easysplit.R.layout;
import com.cz.easysplit.Events.VenmoLibrary.VenmoResponse;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class EventActivity extends FragmentActivity {	
	public FragmentManager fm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		fm = getSupportFragmentManager();
	    EventListFragment fragment = (EventListFragment)fm.findFragmentById(R.id.fragmentContainer);
	    if (fragment == null) {
	    	fragment = new EventListFragment();
	        fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit(); 
	    }
	}
	
	@Override  
	// For venmo return
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	    switch(requestCode) {
	        case 1: { //1 is the requestCode we picked for Venmo earlier when we called startActivityForResult
	            if(resultCode == RESULT_OK) {
	                String signedrequest = data.getStringExtra("signedrequest");
	                if(signedrequest != null) {
	                    VenmoResponse response = (new VenmoLibrary()).validateVenmoPaymentResponse(signedrequest, 
	                    																"KsqetdmN6gUJMbwx3VqRETrs7thcmZXb");
	                    if(response.getSuccess().equals("1")) {
	                        //Payment successful.  Use data from response object to display a success message
	                        String note = response.getNote();
	                        String amount = response.getAmount();
	                    }
	                }
	                else {
	                    String error_message = data.getStringExtra("error_message");
	                    //An error ocurred.  Make sure to display the error_message to the user
	                }                               
	            }
	            else if(resultCode == RESULT_CANCELED) {
	                //The user cancelled the payment
	            }
	        break;
	        }           
	    }
	}
	
}
