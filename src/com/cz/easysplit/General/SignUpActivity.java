package com.cz.easysplit.General;
import com.cz.easysplit.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {
	private String username;
	private String password;
	private String cpassword;
	private Button passToParse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText usernameText = (EditText) findViewById(R.id.username);
        final EditText passwordText = (EditText) findViewById(R.id.password);
        final EditText cpasswordText = (EditText) findViewById(R.id.confirm);
        passToParse = (Button)findViewById(R.id.done);
        passToParse.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                cpassword = cpasswordText.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                if (username.equals("")){
		            builder.setMessage("Username cannot be empty!");
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
					cpasswordText.setText("");
                }
                else if (password.equals("")){
                	builder.setMessage("Password cannot be empty!");
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
					cpasswordText.setText("");
                }
                else{
                	if (password.equals(cpassword)){
            			ParseUser user = new ParseUser();
            			user.setUsername(username);
            			user.setPassword(password);
            			try {
    						user.signUp();
    						Intent i = new Intent(SignUpActivity.this, MainActivity.class);
    		        		startActivity(i);
    					}catch (ParseException e) {
    						builder.setMessage("Username Taken");
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
    						cpasswordText.setText("");
    					}
            		}
            		else{
    		            builder.setMessage("Passwords don't match!");
    		            builder.setCancelable(true);
    		            builder.setNegativeButton("Okay",
    		                    new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog, int id) {
    		                    dialog.cancel();
    		                }		            
    		            });

    		            AlertDialog alert = builder.create();
    		            alert.show();
    					passwordText.setText("");
    					cpasswordText.setText("");
            		}
                }
        	}
        	
        });
        
    }

}
