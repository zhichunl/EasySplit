package com.cz.easysplit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	private Button eventButton;
	private Button paymentButton;
	private Button settingButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        eventButton = (Button)findViewById(R.id.event_button);
        eventButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent i = new Intent(MainActivity.this, EventListActivity.class);
        		startActivity(i);
        	}
        });
        
        paymentButton = (Button)findViewById(R.id.payment_button);
        paymentButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, PaymentListActivity.class);
				startActivity(i);
			}
		});
        
        settingButton = (Button)findViewById(R.id.setting_button);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*if (id == R.id.sett) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
