package edu.jhu.cs.oose.project.group14.ihungry.androidapp;

import java.io.*;

import com.example.androidihungry.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.*;

/**
 * This activity is responsible for view/activity transition.
 * 
 * @author SuNFloWer
 * 
 */
public class ActivityController extends Activity {

	Intent intent_i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * First activity -> login page
		 * 
		 * If have username and pwd , then auto fill the blank Otherwise, leave
		 * the blank empty (User will prompt to sign up)
		 */
		saveFile(
				"info.txt",
				"szhao12||12345||Shang Zhao||Male||911-911-9119||Johns Hopkins University, Baltimore, MD, 21218");

		// Load the first Screen / Activity
		intent_i = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(intent_i, ActivitySwitchSignals.LOGIN);

	}

	/**
	 * Save input string into a file and store in internal storage in Android
	 * 
	 * @param fileName
	 * @param str
	 * @return
	 */
	private boolean saveFile(String fileName, String str) {
		boolean saveSucceed = false;
		try {
			FileOutputStream outStream = this.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			outStream.write(str.getBytes());
			outStream.close();
			DisplayToastOnScr("Saved");
			saveSucceed = true;
		} catch (Exception e) {
			Log.v("[Exception]", "Error saving file");
		}
		return saveSucceed;
	}

	/**
	 * Read content according to the file name from the internal storage from
	 * Android
	 * 
	 * @param fileName
	 * @return
	 */
	private String loadFile(String fileName) {
		String readStr = null;
		try {
			FileInputStream inStream = this.openFileInput(fileName);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				stream.write(buffer, 0, length);
			}
			stream.close();
			inStream.close();
			readStr = stream.toString();
			DisplayToastOnScr("Loaded " + readStr);
		} catch (Exception e) {
			Log.v("[Exception]", "Error reading file");
		}
		return readStr;
	}

	/**
	 * Function to read the result from newly created activity.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.v("[ActivityController] Test", requestCode + " " + resultCode);

		switch (requestCode) {
		case ActivitySwitchSignals.LOGIN:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		case ActivitySwitchSignals.MAINSCREEN:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		case ActivitySwitchSignals.NEARBY:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		case ActivitySwitchSignals.ABOUTME:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		case ActivitySwitchSignals.FAVOURITES:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		case ActivitySwitchSignals.ORDERHISTORY:
			this.switchSceenOnSwhCode(intent_i, resultCode);
			break;
		default:
			break;
		}
	}

	/**
	 * Switch to the specific activity based on the switch code.
	 * 
	 * @param i
	 * @param swhCode
	 */
	private void switchSceenOnSwhCode(Intent i, int swhCode) {
		switch (swhCode) {
		case ActivitySwitchSignals.QUIT:
			Log.v("[ActivityController]", "Finish");
			this.finish();
			break;
		case ActivitySwitchSignals.LOGINSWH:
			i = new Intent(getApplicationContext(), LoginActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.LOGIN);
			break;
		case ActivitySwitchSignals.MAINSCREENSWH:
			i = new Intent(getApplicationContext(), MainScreenActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.MAINSCREEN);
			break;
		case ActivitySwitchSignals.NEARBYSWH:
			i = new Intent(getApplicationContext(), NearbyActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.NEARBY);
			break;
		case ActivitySwitchSignals.ABOUTMESWH:
			i = new Intent(getApplicationContext(), AboutmeActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.ABOUTME);
			break;
		case ActivitySwitchSignals.FAVOURITESSWH:
			i = new Intent(getApplicationContext(), FavouritesActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.FAVOURITES);
			break;
		case ActivitySwitchSignals.ORDERHISTORYSWH:
			i = new Intent(getApplicationContext(), OrderHistoryActivity.class);
			startActivityForResult(i, ActivitySwitchSignals.ORDERHISTORY);
			break;	
		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_controller, menu);
		return true;
	}

	/**
	 * Display toast on screen.
	 * @param str
	 */
	private void DisplayToastOnScr(String str) {
		Toast.makeText(ActivityController.this, str, Toast.LENGTH_SHORT).show();
	}

}
