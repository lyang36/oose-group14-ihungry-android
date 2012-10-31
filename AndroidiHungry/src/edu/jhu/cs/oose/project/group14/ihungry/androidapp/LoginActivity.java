package edu.jhu.cs.oose.project.group14.ihungry.androidapp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import sun.org.mozilla.javascript.internal.Token;

import com.example.androidihungry.R;
import com.example.androidihungry.R.layout;
import com.example.androidihungry.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * This is the login screen - the first view that the user sees after entering the iHungry application.
 * @author SuNFloWer
 *
 */
public class LoginActivity extends Activity {

	private EditText et_username;
	private EditText et_pwd;
	private String username_stored = null;
	private String pwd_stored = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    
        et_username = (EditText) findViewById(R.id.editText1);
        et_pwd = (EditText) findViewById(R.id.editText2);
        //et_username.setHint("Please enter username");
        //et_pwd.setHint("Please enter password");
       
		String readStoredInfo = loadFile("info.txt");
		if(!readStoredInfo.equals(null)){
			String delims = "[|]+";
			String[] tokens = readStoredInfo.split(delims);
			for(int i=0; i<tokens.length; i++){
				Log.v("[Login]",tokens.length+" "+tokens[i]);

			}
			// Set the stored username & password in local variable
			username_stored = tokens[0];
			pwd_stored = tokens[1];
			
			et_username.setText(username_stored);
			et_pwd.setText(pwd_stored);
			
			
		}
    
    
		((Button) findViewById(R.id.button1)).setOnClickListener(btn_Login_Listener);
		((Button) findViewById(R.id.button2)).setOnClickListener(btn_Signup_Listener);

		
    }

	/**
	 * A call-back for when the user presses the login button. A signal to switch to main Screen is sent to ActivityController if the username and pwd are valid
	 */
	OnClickListener btn_Login_Listener = new OnClickListener() {
		public void onClick(View v) {
			if(et_username.getText().toString().equals(username_stored) && et_pwd.getText().toString().equals(pwd_stored)){
				DisplayToastOnScr("Login Successfully");				
				setResult(ActivitySwitchSignals.MAINSCREENSWH);
				finish();
			} else{
				DisplayToastOnScr("Login Failed");				
				
			}
			
		}
	};
	
	/**
	 * A call-back for when the user presses the signup button. An intent is create to start the signup activity
	 */
	OnClickListener btn_Signup_Listener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
			startActivity(intent);

		}
	};
    
    
    
    /**
	 * Read content according to the file name from the internal storage from Android
	 * @param fileName
	 * @return
	 */
	private String loadFile(String fileName)
	{
		String readStr = null;
	    try {
	        FileInputStream inStream=this.openFileInput(fileName);
	        ByteArrayOutputStream stream=new ByteArrayOutputStream();
	        byte[] buffer=new byte[1024];
	        int length=-1;
	        while((length=inStream.read(buffer))!=-1)   {
	            stream.write(buffer,0,length);
	        }
	        stream.close();
	        inStream.close();
	        readStr = stream.toString();
	        DisplayToastOnScr("Loaded "+readStr);
	    }  catch (Exception e){
	    	Log.v("[Exception]","Error reading file");
	    }  
	    return readStr;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			
			setResult(ActivitySwitchSignals.QUIT);
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
    
    /**
     * Display toast on screen.
     * @param str
     */
    private void DisplayToastOnScr(String str) {
		Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
	}
}
