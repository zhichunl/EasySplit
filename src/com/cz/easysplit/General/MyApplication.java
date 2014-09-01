package com.cz.easysplit.General;

import com.cz.easysplit.Events.Prepaid;
import com.cz.easysplit.Payments.Payment;
import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Application;

public class MyApplication extends Application {
	private static MyApplication singleton;
	
	public MyApplication getInstance() {
		return singleton;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
		ParseObject.registerSubclass(Payment.class);
		ParseObject.registerSubclass(Prepaid.class);
	    Parse.initialize(this, "TE6sMM3aeJbNGcb0XQE4IILPlqpO1dBrv0iR69Gd", "R2tMg0521ieHPCeKTjkaeIC4zvqJY3jHBPCyjMXy");
	}
}
