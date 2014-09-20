package com.cz.easysplit.Events;

import java.util.ArrayList;

import com.cz.easysplit.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

public class TransactionListFragment extends ListFragment {
	private ArrayList<Transaction> transactions;
	private ArrayAdapter<Transaction> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.event_list_action_bar, menu);
	}
	
}
