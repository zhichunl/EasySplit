package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;
import com.cz.easysplit.General.MainActivity;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TransactionListFragment extends ListFragment {
	private ArrayList<Transaction> transactions;
	private ArrayAdapter<Transaction> adapter;
    public Event eventForThis;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		transactions = new ArrayList<Transaction>();
		transactions = eventForThis.getTransactions();
		adapter = new TransactionAdapter(transactions);
		setListAdapter(adapter);
		
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		 }
		 setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    //inflater.inflate(R.menu.event_list_action_bar, menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		FragmentManager fm = getActivity().getSupportFragmentManager();
	    		FragmentTransaction transaction = fm.beginTransaction();
	    		
	    	    EventListFragment fragment = (EventListFragment)fm.findFragmentById(R.layout.activity_fragment);
	    	    
	    	    if (fragment == null) {
	    	    	fragment = new EventListFragment();
	    	    }    	    	   
	    	    transaction.replace(R.id.fragmentContainer, fragment);
	    	    transaction.addToBackStack(null);
	    	    transaction.commit();
	    	    return true;
	    	default:
	        	return super.onOptionsItemSelected(item);
	    }
	}
	
	private class TransactionAdapter extends ArrayAdapter<Transaction> {
		
		public TransactionAdapter(ArrayList<Transaction> transactions) {
			super(getActivity(), 0, transactions);
		}
		
		@Override 
		public int getViewTypeCount() {
			return 2;  
		}
		
		@Override
		public int getItemViewType(int position) {
			 Transaction trans = transactions.get(position);

			 if (trans.getPayer().getObjectId().equals(ParseUser.getCurrentUser().getObjectId())) {
				 return 0;
			 } else {
				 return 1;
			 }
		}

		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			int type = getItemViewType(position);
			switch (type) {
				case 0: {
					convertView = getActivity().getLayoutInflater()
							.inflate(R.layout.list_item_transaction, null);	
					Transaction p = transactions.get(position);
					TextView nameView = (TextView)convertView.findViewById(R.id.list_item_trans_name);
					nameView.setText(p.getPayee().getUsername());
					TextView amountView = (TextView)convertView.findViewById(R.id.list_item_trans_amount);
					amountView.setText((new Double(p.getAmount())).toString());
					Button pay = (Button)convertView.findViewById(R.id.list_item_pay);
					pay.setOnClickListener(new View.OnClickListener() {
				        	@Override
				        	public void onClick(View v) {
				        		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				        		alert.setTitle("amount");
				        		alert.setMessage("enter amount");
				        		final EditText input = new EditText(getActivity());
				        		alert.setView(input);
				        		
				        		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				        			public void onClick(DialogInterface dialog, int whichButton) {
				        				//use venmo here
				        				try {
				        			        Intent venmoIntent = VenmoLibrary.openVenmoPayment("2019", "EasySplit","4129445577", "0.1", "Testing", "pay");
				        			        startActivityForResult(venmoIntent, 1); //1 is the requestCode we are using for Venmo. Feel free to change this to another number. 
				        			    }
				        			    catch (android.content.ActivityNotFoundException e) //Venmo native app not install on device, so let's instead open a mobile web version of Venmo in a WebView
				        			    {
				        			        Intent venmoIntent = new Intent(getActivity(), VenmoWebViewActivity.class);
				        			        String venmo_uri = VenmoLibrary.openVenmoPaymentInWebView("2019", "EasySplit","4129445577", "0.1", "Testing", "pay");
				        			        venmoIntent.putExtra("url", venmo_uri);
				        			        startActivityForResult(venmoIntent, 1);
				        			    }
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
					break;
				}
				case 1:{
					convertView = getActivity().getLayoutInflater()
							.inflate(R.layout.list_item_transaction2, null);	
					Transaction p = transactions.get(position);
					
					TextView payerNameView = (TextView)convertView.findViewById(R.id.list_item_trans2_payer);
					payerNameView.setText(String.valueOf(p.getPayer().getUsername()));
					
					TextView payeeNameView = (TextView)convertView.findViewById(R.id.list_item_trans2_payee);
					payeeNameView.setText(p.getPayee().getUsername());
					
					TextView actionView = (TextView)convertView.findViewById(R.id.list_item_trans2_action);
					if (p.getFinished()) {
						actionView.setText(" paid ");
					} else {
						actionView.setText(" has to pay ");
					}
					
					TextView amountView = (TextView)convertView.findViewById(R.id.list_item_trans2_amount);
					amountView.setText((new Double(p.getAmount())).toString());
				}
			}
			
			
			return convertView;
		}
		
	}
}
