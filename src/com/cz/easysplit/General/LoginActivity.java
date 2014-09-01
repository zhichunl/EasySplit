package com.cz.easysplit.General;

import com.cz.easysplit.R;
import com.cz.easysplit.R.id;
import com.cz.easysplit.R.layout;
import com.cz.easysplit.R.menu;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private Button login;
	private Button signup;
	private String username;
	private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usernameText = (EditText) findViewById(R.id.username);
        final EditText passwordText = (EditText) findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		try {
					ParseUser.logIn(usernameText.getText().toString(), passwordText.getText().toString());
					Intent i = new Intent(LoginActivity.this, MainActivity.class);
	        		startActivity(i);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
		            builder.setMessage("Wrong Username/Password Combination");
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
					passwordText.setText("");
				}
        	}
        	
        });
        
        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
        
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
