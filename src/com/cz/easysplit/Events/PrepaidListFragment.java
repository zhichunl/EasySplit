package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;
import com.parse.ParseUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewGroupCompat;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PrepaidListFragment extends ListFragment {
	private ArrayList<Prepaid> myPrepaids;
	private PrepaidAdapter adapter;
	
	public PrepaidListFragment(ArrayList<Prepaid> prepaids) {
		myPrepaids = prepaids;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		if (myPrepaids == null) {
			myPrepaids = new ArrayList(); 
			Prepaid prepaid = new Prepaid();
			prepaid.setUser(ParseUser.getCurrentUser());
			prepaid.setAmountPaid(0);
			myPrepaids.add(prepaid);
		}
		adapter = new PrepaidAdapter(myPrepaids);
		setListAdapter(adapter);
	}
	
	public ArrayList<Prepaid> getPrepaids() {
		return myPrepaids;
	}
	
	public void updatePrepaidList() {
	    adapter.notifyDataSetChanged();
	}
	
	private class PrepaidAdapter extends ArrayAdapter<Prepaid> {
		
		public PrepaidAdapter(ArrayList<Prepaid> prepaids) {
			super(getActivity(), 0, prepaids);
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
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
			plusButton.setOnClickListener(new View.OnClickListener() {
		        	@Override
		        	public void onClick(View v) {
		        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		        		alert.setTitle("amount");
		        		alert.setMessage("enter amount");
		        		final EditText input = new EditText(getActivity());
		        		alert.setView(input);
		        		
		        		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        			public void onClick(DialogInterface dialog, int whichButton) {
		        				String value = input.getText().toString();
		        				double amount;
		        				if (value == null || value.isEmpty()) {
		        					amount = 0.0;
		        				} else {
		        					try {
			        					amount = Double.parseDouble(value);
		        					}
		        					catch (NumberFormatException e) {
		        						amount = 0;
		        					}
		        				}
		        				Prepaid prepaid = myPrepaids.get(position);
		        				prepaid.setAmountPaid(prepaid.getAmountPaid()+amount);
			            		updatePrepaidList();
		        			}
		        		});

	        			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        			  public void onClick(DialogInterface dialog, int whichButton) {
	        			    // Canceled.
	        			  }
	        			});

		        			alert.show();
		        	}
			});
			return convertView;
		}
		
	}
}
