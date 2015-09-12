package com.example.myapp;


import java.net.URL;
import java.util.Arrays;
import org.json.JSONObject;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView info;
	private LoginButton loginButton;
	private CallbackManager callbackManager;
	String  text, userID;	
	String id,name,email,birthday, gender;
	static JSONObject json;
	static URL imageURL;
	ImageView userpicture;
	Bitmap mIcon1;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getApplicationContext());
		callbackManager = CallbackManager.Factory.create();
		setContentView(R.layout.activity_main);
		
		info = (TextView)findViewById(R.id.info);
		loginButton = (LoginButton)findViewById(R.id.login_button);		
		loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));
		
	
	loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
	    @Override
	    public void onSuccess(LoginResult loginResult) {
	   
	    	   	
	    	  GraphRequest request = GraphRequest.newMeRequest(
                      loginResult.getAccessToken(),
                      new GraphRequest.GraphJSONObjectCallback() {
                          @Override
                          public void onCompleted(
                                  JSONObject object,
                                  GraphResponse response) {
                              // Application code  
                        	  json = response.getJSONObject();
                        	  
                        	  Intent i  = new Intent(getApplicationContext(), userDetail.class);
                      	      startActivity(i);                            
                                                                                                        
                          }
                      });
         	  
	    	  Bundle parameters = new Bundle();
             parameters.putString("fields", "id,name,email,gender, birthday");
          request.setParameters(parameters);
          request.executeAsync();
          
        
	    }
	 
	    @Override
	    public void onCancel() {
	    	info.setText("Login attempt canceled.");
	    }
	 
	    @Override
	    public void onError(FacebookException e) {
	    	info.setText("Login attempt failed.");
	    }
	});
		
}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    callbackManager.onActivityResult(requestCode, resultCode, data);
	}
	
	
	}


