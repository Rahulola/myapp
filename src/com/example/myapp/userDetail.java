package com.example.myapp;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class userDetail extends Activity {

TextView information;
ImageView userpicture;
String id,name,email,birthday,gender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userdetail);
	
		
         try {
        	 id = MainActivity.json.getString("id");  
             name =  MainActivity.json.getString("name");
             email=  MainActivity.json.getString("email");
			birthday = MainActivity.json.getString("birthday");
			gender = MainActivity.json.getString("gender");     
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         information = (TextView)findViewById(R.id.info); 
        information.setText(
	    		    "User Name: "
	    		    +  name
	    		    + "\n" +
	    		    "Gender :   "
	    		    + gender
	    		  + "\n" +
	    		    "DOB :      "+ birthday +  "\n" + "Email :    " +email
	    		   	    		    
	    		); 
         
      
       
	}
}