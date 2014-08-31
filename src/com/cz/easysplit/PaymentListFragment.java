package com.cz.easysplit;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class PaymentListFragment extends ListFragment {
	private ArrayList<Payment> myPayments;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("my Payments");
		myPayments = PaymentLab.get(getActivity()).getPayments();
		
		ArrayAdapter<Payment> adapter = new ArrayAdapter<Payment>(getActivity(),
											android.R.layout.simple_list_item_1,
											myPayments);
		setListAdapter(adapter);
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		    }
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    //inflater.inflate(R.menu.Payment_action_bar, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	if (NavUtils.getParentActivityName(getActivity()) != null) {
	                NavUtils.navigateUpFromSameTask(getActivity());
	            }	        	
	        	return true;
	        default:
	        	return super.onOptionsItemSelected(item);

	    } 
	}
	
	
}
