package com.mygdx.game;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.parse.Parse;


public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//initializing a connection with the Parse Server
		Parse.initialize(new Parse.Configuration.Builder(this)
				.applicationId("ad319a67d93583db1d493b110cbbdc225db15c16")
				.clientKey("4d2d812b590a7b83ca06717b1c0c10211f5d5462")
				.server("http://18.188.38.4:80/parse")
				.build()
		);
		//starting the application
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		if(isNetworkAvailable()) {// if there is an internet connection
			//making sure the device has enough memory to be used
			ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();
			if (!memoryInfo.lowMemory) {
				initialize(new MainMenu(), config);// start the app
			}
		} else {
			initialize(new InternetProblemScreen(), config);// else start the not internet connection
		}
	}

	/**
	 * Use it to hide the task bar
	 * @param hasFocus if the window has focus or no
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		// if the windows has focus hide the task bar in the bottom
		if (hasFocus) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	/**
	 * method to test if there is an internet connection
	 * @return a boolean with weather there is a connection
	 */
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	/**
	 * checking if the memory is okay
	 * @return information about memory
	 */
	private ActivityManager.MemoryInfo getAvailableMemory() {
		ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo;
	}
}
